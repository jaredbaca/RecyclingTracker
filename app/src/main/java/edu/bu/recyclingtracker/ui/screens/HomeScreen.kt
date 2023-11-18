package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.bu.recyclingtracker.RecyclableItemViewModel
import edu.bu.recyclingtracker.data.icons
import edu.bu.recyclingtracker.data.navItems
import edu.bu.recyclingtracker.data.recyclables
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.AppToolbar
import edu.bu.recyclingtracker.ui.components.ItemCard
import edu.bu.recyclingtracker.ui.components.ItemGrid
import edu.bu.recyclingtracker.ui.components.bottomNavBar
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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemGrid(icons, "Plastics", viewModel.getItemNames(), viewModel)
            }
        }
    }
}