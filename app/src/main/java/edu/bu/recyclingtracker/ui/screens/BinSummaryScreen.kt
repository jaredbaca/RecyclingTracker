package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.data.recyclables
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemGrid
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSummaryScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

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
                        text = "Bin Summary Screen",
                        fontSize = 24.sp
                    )
                }

                val dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM d, yyyy")


                Row {
                    Text(text = dateFormat.format(Date()))
                }

                viewModel.uiState.value.itemCounts.value.forEach {
                    if(it.quantity > 0) {
                        Row {
                            Text(text = "${it.name}: ${it.quantity}")
                        }
                    }
                }

            }

        }
    }
}

@Preview
@Composable
fun BinSummaryScreenPreview() {
//    BinSummaryScreen()
}

