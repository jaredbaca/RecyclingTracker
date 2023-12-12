package edu.bu.recyclingtracker.ui.screens

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

/*
Navigation Graph used in the main RecyclingTracker App composable. Contains composables for all screens.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclingTrackerNavigationGraph(navController: NavHostController, recyclablesViewModel: LogRecyclablesViewModel) {

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {

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
            StatsScreen(navController, recyclablesViewModel)
        }
    }
}
