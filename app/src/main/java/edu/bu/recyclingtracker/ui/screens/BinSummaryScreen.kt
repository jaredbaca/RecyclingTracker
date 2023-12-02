package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemCard
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        } ,
                
        floatingActionButton = {
            FloatingActionButton(onClick = {

                GlobalScope.launch {
                    viewModel.addEntryFromCurrentBin()
                    viewModel.updateTotals()
                    viewModel.totalsByCategory = mutableStateOf(viewModel.getTotalsByCategory())
                    viewModel.resetCounts()
                }

                navController.navigate(Routes.HOME_SCREEN)
            })
            {
                Icon(imageVector = Icons.Default.Upload, contentDescription = null)
            }
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

                LazyColumn(content = {
                    items(viewModel.uiState.value.itemCounts.value.size) {
                        index ->
                        if(viewModel.uiState.value.itemCounts.value[index].quantity > 0) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
//                            Text(text = "${it.name}: ${it.quantity}")
                            Text(text=viewModel.uiState.value.itemCounts.value[index].quantity.toString(),
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(start = 16.dp))

                            ItemCard(
                                selected = false,
                                name = "",
                                itemUiState = viewModel.uiState.value.itemCounts.value[index],
                                viewModel = viewModel,
                                index = 0,
                                counterVisible = false
                            )
                            Text(text = viewModel.uiState.value.itemCounts.value[index].name)
                        }
                        Divider()
                    }
                        }
                    item {
                        Row(modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(text = "Total: ${viewModel.uiState.value.itemCounts.value.sumOf { it.quantity }} Items",
                            fontSize = 24.sp)
                        }
                    }
                })
            }

        }
    }
}

@Preview
@Composable
fun BinSummaryScreenPreview() {
//    BinSummaryScreen()
}

