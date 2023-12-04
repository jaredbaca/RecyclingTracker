package edu.bu.recyclingtracker.ui.components

import androidx.annotation.ColorLong
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.bu.recyclingtracker.data.NavItem
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.screens.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.screens.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(toolbarTitle:String) {
    TopAppBar(
        title = {Text(text = toolbarTitle)},
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu,
                contentDescription = "navigation icon",
                modifier = Modifier
                    .clickable {  },

                tint = Color.Black,
            )
        },
    )
}

@Composable
fun bottomNavBar() {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Recycling, contentDescription = "Recycling Page")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Recycling Page")
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Timeline, contentDescription = "Recycling Page")
            }
        }
    }
}

@Composable
fun bottomNavBar2(
//    navItems: List<NavItem>,
                  navController: NavController, viewModel: LogRecyclablesViewModel) {

    var navItems = listOf<NavItem>(
        NavItem("Home", Icons.Default.Home, Routes.HOME_SCREEN),
        NavItem("Bin", Icons.Default.ShoppingCart, Routes.BIN_SUMMARY_SCREEN, badgeCount = viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }),
        NavItem("Stats", Icons.Default.Timeline, Routes.STATS_SCREEN)
    )

    BottomNavigation(
        backgroundColor = Color(android.graphics.Color.parseColor("#63ad1e"))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navItems.forEach { navItem -> 
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                onClick = {
                          navController.navigate(navItem.route){
                              restoreState = true
                          }
                },
                icon = {
                    Column {
                        if(navItem.badgeCount > 0) {
                            BadgedBox(badge = { Badge {
                                Text(navItem.badgeCount.toString(),
                                    color = Color.White,
                                    fontSize = 10.sp
                            )
                            }
                            }) {
                                Icon(navItem.icon, contentDescription = "")
                            }
                        } else {
                            Icon(navItem.icon, contentDescription = "")
                        }
                    }

                }
            )
        }
    }
}

@Composable
fun ScaffoldLayout() {

}
