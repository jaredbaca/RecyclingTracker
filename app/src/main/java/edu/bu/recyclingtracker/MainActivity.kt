package edu.bu.recyclingtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Recycling
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import dagger.hilt.android.AndroidEntryPoint
import edu.bu.recyclingtracker.authentication.LoginViewModel
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.components.drawerItem
//import edu.bu.recyclingtracker.ui.screens.LogRecyclablesScreen
import edu.bu.recyclingtracker.ui.navigation.RecyclingTrackerNavigationGraph
import edu.bu.recyclingtracker.ui.navigation.Routes
import edu.bu.recyclingtracker.ui.theme.RecyclingTrackerTheme
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * The Recycling Tracker App allows the user to track their personal recycling activity,
 * view trends and data about their activity, and estimate the environmental impact
 * of their recycling habits.
 *
 * This app is built with Jetpack Compose and follows an MVVM architecture.
 * It uses a single activity with Composables for individual screens.
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecyclingTrackerTheme {
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


    // ========================== Top Level RecyclingTrackerApp Composable ===================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclingTrackerApp() {

    // ========================== Instantiating Firestore Database ============================

    // Firestore Database
    val db = Firebase.firestore

    db.firestoreSettings = firestoreSettings {
        isPersistenceEnabled = false
    }
    //Using emulator for testing mode
//    db.useEmulator("10.0.2.2", 8080)

    // ========================= Creating ViewModels ==========================================

    // Login ViewModel (Hilt)
    val loginViewModel: LoginViewModel = hiltViewModel()

    // Database Repository
    var recyclingTrackerRepository = RecyclingTrackerRepository(RecyclingTrackerDao(db, loginViewModel))

    // Item Repository (used to hold item counts for the UI and handles Firestore access
    val recyclablesViewModel: LogRecyclablesViewModel = viewModel { LogRecyclablesViewModel(recyclingTrackerRepository) }

    // ========================= Initializing Data From Firestore =============================

    // This data is retrieved early so the stats page has data to display
    LaunchedEffect(true) {
        GlobalScope.async {
            recyclablesViewModel.updateTotals()
            recyclablesViewModel.totalsByCategory.value = recyclablesViewModel.getTotalsByCategory()
            recyclablesViewModel.weights = mutableStateOf(recyclablesViewModel.calculateWeights())
        }
    }

    // ========================= Navigation and Scaffold States ================================

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
//    var currentUser by remember {loginViewModel.currentUser}

    // ========================= Scaffold Layout For All Screens ==============================

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = navBarColor
            ) {
                IconButton(onClick = { scope.launch { drawerState.apply { close() } } }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
                
                if(loginViewModel.currentUser.value != null) {
                    Text(text = "Welcome,", modifier = Modifier.padding(start = 36.dp, top = 16.dp), fontSize = 36.sp)
                    Text(text = "${loginViewModel.currentUser?.value?.email?.split("@")?.get(0)}",
                        modifier = Modifier
                            .padding(start = 36.dp, bottom = 32.dp)
                        , fontSize = 24.sp)

//                    drawerItem(name = "History", route = Routes.HOME_SCREEN, navController = navController, scope, drawerState)
                    drawerItem(name = "Settings", route = Routes.HOME_SCREEN, navController = navController, scope, drawerState)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(text = "Log Out", fontSize = 18.sp, modifier = Modifier
                            .padding(start = 36.dp, top = 16.dp, bottom = 16.dp)
                            .clickable {
                                loginViewModel.logoutUser()
                                recyclablesViewModel.resetVMTotals()
                                navController.navigate(Routes.LOGIN_SCREEN)
//                            Log.d("Log Out", loginViewModel.currentUser.value?.email.toString())
                                scope.launch { drawerState.apply { close() } }
                            })
                    }

                }
            }
        },
    ) {

        // Elements of the Scaffold Layout can be found in the scaffoldLayout.kt file
        Scaffold(
            topBar = {
                AppToolbar(toolbarTitle = "", scope, drawerState)
            },
            bottomBar = {
                if(currentDestination?.hierarchy?.any { it.route == Routes.LOGIN_SCREEN || it.route == Routes.SIGNUP_SCREEN } == false) {
                    bottomNavBar2(navController, recyclablesViewModel)
                }
            },

            floatingActionButton = {
                if(currentDestination?.hierarchy?.any { it.route == Routes.BIN_SUMMARY_SCREEN || it.route == Routes.HOME_SCREEN } == true) {

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
                    Icon(imageVector = Icons.Default.Recycling, contentDescription = null)
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