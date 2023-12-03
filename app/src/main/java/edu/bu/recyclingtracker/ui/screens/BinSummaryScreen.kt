package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemCard
import edu.bu.recyclingtracker.ui.components.ItemCardNoCounter
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.theme.FABColor
import edu.bu.recyclingtracker.ui.theme.GlassColor
import edu.bu.recyclingtracker.ui.theme.categoryColors
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
            FloatingActionButton(
                containerColor = FABColor,
                onClick = {

                GlobalScope.launch {
                    viewModel.addEntryFromCurrentBin()
                    viewModel.updateTotals()
                    viewModel.totalsByCategory = mutableStateOf(viewModel.getTotalsByCategory())
                    viewModel.resetCounts()
                }

                navController.navigate(Routes.HOME_SCREEN)
            })
            {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
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

                var itemList by remember { mutableStateOf(viewModel.uiState.value.itemCounts.value) }

                LazyColumn(content = {
                    items(itemList.size) {
                        index ->
                        if(itemList[index].quantity > 0) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
//                            Text(text = "${it.name}: ${it.quantity}")



//                            var text by remember { mutableStateOf(viewModel.uiState.value.itemCounts.value[index].quantity.toString()) }
//
//                           TextField(value = text,
//                               onValueChange = {
//                                   viewModel.updateItemQuantity(viewModel.uiState.value.itemCounts.value[index].name, it)
////                                   text = it
//                               },
//                               modifier = Modifier
//                                   .size(48.dp)
//                                   .padding(start = 8.dp),
//                               keyboardOptions = KeyboardOptions(
//                                   keyboardType = KeyboardType.Number
//                               )
//                           )

                            Column(
//                                modifier = Modifier.weight(1f)
                            )   {

                                Text(text=itemList[index].quantity.toString(),
                                    fontSize = 18.sp,
                                    modifier = Modifier
                                        .padding(start = 16.dp)
//                                        .weight(1f),
                                )
                            }

                                Column(
//                                    modifier = Modifier.weight(1f)
                                )   {

                                    ItemCardNoCounter(
                                        selected = false,
                                        name = "",
                                        itemUiState = itemList[index],
                                        viewModel = viewModel,
                                        index = 0,
                                        color = categoryColors[itemList[index].category] ?: Color.Green
                                    )
                                }

                                Column(
                                    modifier = Modifier.weight(1f)
                                )   {

                                    Text(text = itemList[index].name
                                    )
                                }



                                //Delete Button
                                //TODO Need to make this update in real time
                            IconButton(onClick = {
                                itemList[index].quantity = 0;
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove Item")
                            }

                        }

                        Divider()
                    }
                        }
                    item {
                        Row(modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(text = "Total: ${itemList.sumOf { it.quantity }} Items",
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

