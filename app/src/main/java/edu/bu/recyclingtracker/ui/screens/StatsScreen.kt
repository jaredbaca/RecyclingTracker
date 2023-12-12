package edu.bu.recyclingtracker.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.BarGraph
import edu.bu.recyclingtracker.ui.components.BarType
import edu.bu.recyclingtracker.ui.components.CenteredDivider
import edu.bu.recyclingtracker.ui.components.PieChart
import edu.bu.recyclingtracker.ui.components.headerText
import edu.bu.recyclingtracker.ui.components.impactText
import edu.bu.recyclingtracker.ui.components.weightText
import edu.bu.recyclingtracker.ui.theme.PlasticColor
import edu.bu.recyclingtracker.ui.theme.categoryColors

/*
Composable for User Stats page that displays item totals, category percentages, and environmental impact estimate.
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StatsScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {
            LazyColumn {
                item {
                    headerText(text = "Categories")
                    CenteredDivider(paddingValue = 128)
                }

                // Pie Chart showing item category percentages
                item {
                    PieChart(
                        data = viewModel.totalsByCategory
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                }
                item {
                    headerText(text = "Item Breakdown")
                    CenteredDivider(paddingValue = 128)
                }

                // Item Breakdown Bar Graphs
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Empty list to hold Bar Graph composables. They will be invoked all at once further down in this section.
                        var barGraphs: MutableList<@Composable () -> Unit> = mutableListOf()

                        // Test Data
                        val vmTotals = viewModel.totals.value
                        val floatValue = mutableListOf<Float>()
                        val labels = mutableListOf<String>(
                            "Item 1",
                            "Item 2",
                            "Item 3",
                            "Item 4",
                            "Item 5",
                            "Item 6",
                            "Item 7",
                            "Item 8"
                        )

                        // List to hold item names
                        val itemsWithinCategory = mutableListOf<String>()

                        // Loop through list of categories and create a bar graph for each
                        for (category in viewModel.totalsByCategory.value.keys) {
                            // Recyclables Data
                            itemsWithinCategory.clear()
                            var graphColor = categoryColors[category]?:Color.Gray

                            // Build list of item names in given category
                            viewModel.uiState.value.itemCounts.value.forEach {
                                if (it.category == category) itemsWithinCategory.add(
                                    it.name
                                )
                            }
                            Log.d("Item List", itemsWithinCategory.toString())

                            // Select totals for given category from overall totals
                            var itemTotal = vmTotals.filter {
                                    itemsWithinCategory.contains(
                                        it.key
                                    ) && it.value.toString().toDouble() > 0
                                }

                            Log.d("$category Totals:", itemTotal.toString())

                            var plasticTotals = mutableListOf<Int>(2,5,4,8,3)

                            // Add bar graph composable to barGraphs list
                            barGraphs.add(
                                {
                                    BarGraph(
                                        graphBarData = convertToFloats(itemTotal.values.mapNotNull {
                                            it.toString().toDouble().toInt()
                                        }.toMutableList()),
                                        xAxisLabels = labels,
                                        barData_ = itemTotal.values.mapNotNull {
                                            it.toString().toDouble().toInt()
                                        }.toMutableList(),
                                        height = 300.dp,
                                        roundType = BarType.TOP_CURVED,
                                        barWidth = 20.dp,
                                        barColor = graphColor,
                                        barArrangement = Arrangement.SpaceEvenly,
                                        category = category
                                    )
                                }
                            )
                        }

                        // Pager View to hold the 4 item category bar graphs and make them scrollable
                        val pagerState = rememberPagerState(pageCount = { 4 })
                        HorizontalPager(state = pagerState) { page ->
                            // Our page content
                            barGraphs[page].invoke()
                        }
                    }
                }

                // Environmental Impact Estimate
                item {
                    headerText(text = "Impact")
                    CenteredDivider(paddingValue = 128)
                }

                item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        ) {

                        impactText(text = "You've recycled ", color = Color.Gray)
                        //Plastic
                        weightText(category = "Plastic", viewModel = viewModel, color = PlasticColor)

                        impactText(text = "so far this year ", color = Color.Gray)
                        
                        CenteredDivider(paddingValue = 256)

                        impactText(text = "That's the equivalent of ", color = Color.Gray)

                        // Calculate total CO2
                        Text("${
                            String.format("%.1f",
                            ((viewModel.weights.value["Plastic"]?:0.0)/2000) * 5774)
                        } lbs of CO2 ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = PlasticColor)
                    }
                }





            } // Lazy Column
        } // Surface

/*
Utility function to convert list of Any to Floats for use in the Bar Graph bar data
 */
private fun convertToFloats(itemTotal: List<Any>): MutableList<Float> {
    var floatValue: MutableList<Float> = mutableListOf()

    itemTotal.forEachIndexed { index, value ->
        floatValue.add(index = index,
            element = value.toString().toFloat() / itemTotal.maxWith(
                compareBy { it as? Comparable<*> }).toString().toFloat()
        )
    }
    return floatValue
}
