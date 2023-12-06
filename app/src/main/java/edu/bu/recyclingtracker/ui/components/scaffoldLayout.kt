package edu.bu.recyclingtracker.ui.components

import androidx.annotation.ColorLong
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DrawerValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.QueryStats
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import edu.bu.recyclingtracker.data.NavItem
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.screens.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.screens.Routes
import edu.bu.recyclingtracker.ui.theme.GlassColor
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
fun bottomNavBar2(
//    navItems: List<NavItem>,
                  navController: NavController, viewModel: LogRecyclablesViewModel) {

    var badgeCount by remember { mutableStateOf(viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }) }

    var navItems = listOf<NavItem>(
        NavItem("Home", Icons.Rounded.Home, Routes.HOME_SCREEN),
        NavItem("Bin", Icons.Rounded.ShoppingBag, Routes.BIN_SUMMARY_SCREEN, badgeCount = viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }),
        NavItem("Stats", Icons.Rounded.QueryStats, Routes.STATS_SCREEN)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout(navController: NavController, viewModel: LogRecyclablesViewModel) {
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ) {
        Scaffold(
            topBar = {
//                AppToolbar(toolbarTitle = "Recyclables")
            },
            bottomBar = {
                bottomNavBar2(
                    navController, viewModel
                )
            }

        ) { paddingValues ->

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                /* Content */
            }
        }
    }
}

//@Composable
//fun navDrawer() {
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    ModalNavigationDrawer(
//        drawerState = drawerState
//        drawerContent = {
//            ModalDrawerSheet {}
//        },
//        ) {
//
//    }
//}
