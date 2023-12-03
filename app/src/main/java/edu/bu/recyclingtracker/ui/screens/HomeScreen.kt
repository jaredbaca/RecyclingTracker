package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.data.recyclables
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemGrid
import edu.bu.recyclingtracker.ui.components.bottomNavBar2
import edu.bu.recyclingtracker.ui.theme.CardboardColor
import edu.bu.recyclingtracker.ui.theme.GlassColor
import edu.bu.recyclingtracker.ui.theme.MetalColor
import edu.bu.recyclingtracker.ui.theme.PlasticColor

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

    val itemGrids: List<@Composable () -> Unit> = listOf(
        {ItemGrid( "Plastics", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Plastic"}), viewModel, color = PlasticColor)},
        {ItemGrid( "Metals", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Metal"}), viewModel, color = MetalColor)},
        {ItemGrid( "Glass", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Glass"}), viewModel, color = GlassColor)},
        {ItemGrid( "Cardboard", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Cardboard"}), viewModel, color = CardboardColor)}
    )

    Scaffold(
        topBar = {
            AppToolbar(toolbarTitle = "Recyclables")
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


//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                ItemGrid( "Plastics", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Plastic"}), viewModel)
//                ItemGrid( "Metals", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Metal"}), viewModel)
//                ItemGrid( "Glass", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Glass"}), viewModel)
//                ItemGrid( "Cardboard", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter{it.category == "Cardboard"}), viewModel),
//                ItemGrid( "Plastics", recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter { it.category == "Plastic" }), viewModel) }

            LazyColumn {
                for (grid in itemGrids) {
                    item {
                        grid.invoke()
                    }
                }
            }
        }
    }
}



