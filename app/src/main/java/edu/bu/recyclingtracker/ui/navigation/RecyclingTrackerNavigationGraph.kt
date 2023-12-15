package edu.bu.recyclingtracker.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.bu.recyclingtracker.authentication.LoginScreen
import edu.bu.recyclingtracker.authentication.LoginViewModel
import edu.bu.recyclingtracker.authentication.SignUpScreen
import edu.bu.recyclingtracker.ui.screens.BinSummaryScreen
import edu.bu.recyclingtracker.ui.screens.HomeScreen
import edu.bu.recyclingtracker.ui.screens.StatsScreen
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel


// ================================ Recycling Tracker Navigation Graph ===============================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclingTrackerNavigationGraph(
    navController: NavHostController,
    recyclablesViewModel: LogRecyclablesViewModel,
) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = if(loginViewModel.currentUser.value == null) Routes.LOGIN_SCREEN else Routes.HOME_SCREEN) {

        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navController)
        }

        composable(Routes.HOME_SCREEN) {
            HomeScreen(navController, recyclablesViewModel)
        }

        composable(Routes.BIN_SUMMARY_SCREEN) {
            BinSummaryScreen(navController, recyclablesViewModel)
        }

        composable(Routes.STATS_SCREEN) {
            StatsScreen(navController, recyclablesViewModel)
        }

        composable(Routes.SIGNUP_SCREEN) {
            SignUpScreen(navController, recyclablesViewModel)
        }
    }
}
