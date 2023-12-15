package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.QueryStats
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.bu.recyclingtracker.R
import edu.bu.recyclingtracker.data.NavItem
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.navigation.Routes
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
This contains the Top App Bar and Bottom Nav Bar composables.
The rest of the scaffold layout was moved to RecyclingTrackerApp composable in the Main Activity
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(toolbarTitle:String, scope: CoroutineScope, drawerState: DrawerState) {
    TopAppBar(
        title = {Text(text = toolbarTitle)},
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu,
                contentDescription = "navigation icon",
                modifier = Modifier
                    .clickable {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                tint = Color.Black,
            )
        },
    )
}

@Composable
fun bottomNavBar2(navController: NavController, viewModel: LogRecyclablesViewModel) {

    var badgeCount by remember { mutableStateOf(viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }) }

    var navItems = listOf<NavItem>(
        NavItem(stringResource(R.string.home), Icons.Rounded.Home, Routes.HOME_SCREEN),
        NavItem(stringResource(R.string.bin), Icons.Rounded.ShoppingBag, Routes.BIN_SUMMARY_SCREEN, badgeCount = viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }),
        NavItem(stringResource(R.string.stats), Icons.Rounded.QueryStats, Routes.STATS_SCREEN)
    )

    BottomNavigation(
        backgroundColor =
//        Color(android.graphics.Color.parseColor("#63ad1e")) //Previous Green
        navBarColor

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
                                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(navItem.icon, contentDescription = "",
                                        tint = if(currentDestination?.hierarchy?.any { it.route == navItem.route } == true) Color.Black else Color.Gray
                                    )
                                    if(currentDestination?.hierarchy?.any { it.route == navItem.route } == true) {
                                        Text(navItem.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                                    }
                                }
                            }
                        } else {
                            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(navItem.icon, contentDescription = "",
                                    tint = if(currentDestination?.hierarchy?.any { it.route == navItem.route } == true) Color.Black else Color.Gray
                                )
                                if(currentDestination?.hierarchy?.any { it.route == navItem.route } == true) {Text(navItem.name, textAlign = TextAlign.Center, fontSize = 10.sp)}
                            }

                        }
                    }

                }
            )
        }
    }
}
