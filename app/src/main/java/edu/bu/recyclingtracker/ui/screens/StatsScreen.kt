package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

    Scaffold(
        topBar = {
            AppToolbar(toolbarTitle = "Home")
        },
        bottomBar = {
            bottomNavBar2(navItems = navItems, navController)
        }

    ) {paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column {
                Row {
                    Text(
                        text = "Stats Screen",
                        fontSize = 24.sp
                    )
                }

                viewModel.totals.forEach {
                    Row {
                        Text(text = "${it.key}: ${it.value}")
                    }
                }

            }
        }
    }

}
