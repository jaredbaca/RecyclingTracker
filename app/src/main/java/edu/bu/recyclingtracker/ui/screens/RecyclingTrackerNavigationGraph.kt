package edu.bu.recyclingtracker.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclingTrackerNavigationGraph(recyclablesViewModel: LogRecyclablesViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
//        composable(Routes.LOG_RECYCLABLES_SCREEN) {
//            LogRecyclablesScreen(recyclablesViewModel)
//        }

        composable(Routes.LOGIN_SCREEN) {
            LoginScreen()
        }

        composable(Routes.HOME_SCREEN) {
            HomeScreen(navController, recyclablesViewModel)
        }

        composable(Routes.BIN_SUMMARY_SCREEN) {
            BinSummaryScreen(navController, recyclablesViewModel)
        }

        composable(Routes.STATS_SCREEN) {
            StatsScreen(navController)
        }

    }
}
