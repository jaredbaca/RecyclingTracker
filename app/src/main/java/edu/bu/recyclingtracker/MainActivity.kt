package edu.bu.recyclingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.components.drawerItem
//import edu.bu.recyclingtracker.ui.screens.LogRecyclablesScreen
import edu.bu.recyclingtracker.ui.screens.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.screens.Routes
import edu.bu.recyclingtracker.ui.theme.RecyclingTrackerTheme
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclingTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecyclingTrackerApp()
                }
            }
        }
    }
}

/*
This is the top level composable for the Recycling Tracker app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclingTrackerApp() {

    // Here we create the dependencies that will be passed further down the stack

    // Instantiating Firestore DB
    val db = Firebase.firestore

    db.firestoreSettings = firestoreSettings {
        isPersistenceEnabled = false
    }
    //Using emulator for testing mode
//    db.useEmulator("10.0.2.2", 8080)

    // Create Repository object
    var recyclingTrackerRepository = RecyclingTrackerRepository(RecyclingTrackerDao((db)))

    // Create view model here so that it can utilize the repository
    val recyclablesViewModel: LogRecyclablesViewModel = viewModel {LogRecyclablesViewModel(recyclingTrackerRepository)}

    // Retrieve initial totals from Firestore to display on stats page
    LaunchedEffect(true) {
        GlobalScope.async {
            recyclablesViewModel.updateTotals()
            recyclablesViewModel.totalsByCategory.value = recyclablesViewModel.getTotalsByCategory()
            recyclablesViewModel.weights = mutableStateOf(recyclablesViewModel.calculateWeights())
        }
    }


    // Instantiating the nav controller and scaffold component states
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = navBarColor
            ) {
                IconButton(onClick = { scope.launch { drawerState.apply { close() } } }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

                drawerItem(name = "Login", route = Routes.LOGIN_SCREEN, navController = navController, scope, drawerState)
                drawerItem(name = "History", route = Routes.HOME_SCREEN, navController = navController, scope, drawerState)
                drawerItem(name = "Settings", route = Routes.HOME_SCREEN, navController = navController, scope, drawerState)
            }
        },
    ) {

        Scaffold(
            topBar = {
                AppToolbar(toolbarTitle = "Recyclables", scope, drawerState)
            },
            bottomBar = {
                bottomNavBar2(navController, recyclablesViewModel)
            },

            floatingActionButton = {
                if(currentDestination?.hierarchy?.any { it.route == Routes.BIN_SUMMARY_SCREEN } == true) {

                FloatingActionButton(
                    containerColor = navBarColor,
                    onClick = {

                        GlobalScope.launch {
                            recyclablesViewModel.addEntryFromCurrentBin()
                            recyclablesViewModel.updateTotals()
                            recyclablesViewModel.totalsByCategory = mutableStateOf(recyclablesViewModel.getTotalsByCategory())
                            recyclablesViewModel.weights = mutableStateOf(recyclablesViewModel.calculateWeights())
                            recyclablesViewModel.resetCounts()
                        }

                        navController.navigate(Routes.HOME_SCREEN)
                    })
                {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                }
            }
    }

        ) { paddingValues ->

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                RecyclingTrackerNavigationGraph(navController, recyclablesViewModel)
            }
        }
    }
}