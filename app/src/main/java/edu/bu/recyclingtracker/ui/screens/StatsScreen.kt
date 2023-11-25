package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.PieChart
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
//            Column {
//                Row {
//                    Text(
//                        text = "Stats Screen",
//                        fontSize = 24.sp
//                    )
//                }
//
//                viewModel.totals.value.forEach {
//                    Row {
//                        Text(text = "${it.key}: ${it.value}")
//                    }
//                }
//
//                Row {
//                    PieChart(
//                        data = mapOf(
//                            Pair("Sample-1", 150),
//                            Pair("Sample-2", 120),
//                            Pair("Sample-3", 110),
//                            Pair("Sample-4", 170),
//                            Pair("Sample-5", 120),
//                        )
//                    )
//                }
//            }

            LazyColumn {
                item {
                    Divider()
                }
                item {
                    PieChart(
                        data = viewModel.totalsByCategory.value
                    )
                }

            } // Lazy Column
        } // Surface
    }
}
