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
import androidx.compose.foundation.lazy.items
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
//import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemCard
import edu.bu.recyclingtracker.ui.components.ItemCardNoCounter
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.theme.FABColor
import edu.bu.recyclingtracker.ui.theme.GlassColor
import edu.bu.recyclingtracker.ui.theme.categoryColors
import edu.bu.recyclingtracker.ui.theme.navBarColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

/*
Composable that creates the summary screen showing currently selected items (much like a shopping cart)
Takes the Nav Controller and ViewModel as arguments
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinSummaryScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

        Column {
            //Date stamp
            val dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM d, yyyy")

            Row {
                Text(text = dateFormat.format(Date()))
            }

            //Access items from ViewModel
            var itemList by remember { mutableStateOf(viewModel.uiState.value.itemCounts.value) }
            var uiState = viewModel.uiState

            //Lazy column so that the content is scrollable
            LazyColumn(content = {
                items(items = uiState.value.itemCounts.value) {
                    item ->
                    // Only display items that have a count greater than 0
                    if(item.quantity > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                        Column(
                        )   {

                            // Item count
                            Text(text=item.quantity.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            )
                        }

                            // Item icon
                            Column() {
                                ItemCardNoCounter(
                                    selected = false,
                                    name = "",
                                    itemUiState = item,
                                    viewModel = viewModel,
                                    index = 0,
                                    color = categoryColors[item.category] ?: Color.Green
                                )
                            }

                            // Item name
                            Column(
                                modifier = Modifier.weight(1f)
                            )   {
                                Text(text = item.name)
                            }

                            // Delete Button
                        IconButton(onClick = {
                            viewModel.updateItemQuantity(item.name, "0")
                        }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove Item")
                        }
                    }

                    Divider()
                    }
                }

                // Total number of items
                item {
                    Row(modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    ) {
                        Text(text = "Total: ${itemList.sumOf { it.quantity }} Items",
                        fontSize = 24.sp)
                    }
                }
            })
        }

        }

@Preview
@Composable
fun BinSummaryScreenPreview() {
//    BinSummaryScreen()
}

