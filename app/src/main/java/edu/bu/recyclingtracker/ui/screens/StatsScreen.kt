package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.CenteredDivider
import edu.bu.recyclingtracker.ui.components.PieChart
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.components.headerText
import edu.bu.recyclingtracker.ui.theme.PlasticColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

    Scaffold(
        topBar = {
            AppToolbar(toolbarTitle = "Your Recycling Stats")
        },
        bottomBar = {
            bottomNavBar2(
//                navItems = navItems,
                navController,
                viewModel)
        }

    ) {paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                item {
                    headerText(text = "Categories")
                    CenteredDivider(paddingValue = 128)
                }
                item {
                    PieChart(
                        data = viewModel.totalsByCategory.value
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                }
                item {
                    headerText(text = "Item Breakdown")
                    CenteredDivider(paddingValue = 128)
                }

                item {
                    headerText(text = "Impact")
                    CenteredDivider(paddingValue = 128)
                }

                var weight = "100 lbs of plastic "

                item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        ) {

                        Text("You've recycled ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray)
                        Text("$weight ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = PlasticColor)
                        Text("so far this year ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray)
                        CenteredDivider(paddingValue = 256)

                        Text("That's the equivalent of ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Gray)
                        Text("100 kg of CO2 ", fontSize = 18.sp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            textAlign = TextAlign.Center,
                            color = PlasticColor)
                    }
                }



            } // Lazy Column
        } // Surface
    }
}
