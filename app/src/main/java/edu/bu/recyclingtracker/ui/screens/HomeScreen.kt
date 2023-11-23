package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {
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

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemGrid( "Plastics", recyclableItems = mutableStateOf(recyclables.value.filter{it.category == "Plastic"}), viewModel)
                ItemGrid( "Metals", recyclableItems = mutableStateOf(recyclables.value.filter{it.category == "Metal"}), viewModel)
                ItemGrid( "Glass", recyclableItems = mutableStateOf(recyclables.value.filter{it.category == "Glass"}), viewModel)
                ItemGrid( "Cardboard", recyclableItems = mutableStateOf(recyclables.value.filter{it.category == "Cardboard"}), viewModel)
            }
        }
    }
}

