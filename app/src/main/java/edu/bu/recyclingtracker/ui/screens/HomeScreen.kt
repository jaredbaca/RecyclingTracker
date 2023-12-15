package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import edu.bu.recyclingtracker.R
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.ItemGrid
import edu.bu.recyclingtracker.ui.theme.CardboardColor
import edu.bu.recyclingtracker.ui.theme.GlassColor
import edu.bu.recyclingtracker.ui.theme.MetalColor
import edu.bu.recyclingtracker.ui.theme.PlasticColor

/**
Composable for the Home Screen, where user can begin adding items for recycling.
 */

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: LogRecyclablesViewModel) {

    // Item Grids for each category are stored as a list of Composables that will then be invoked in the Lazy Column below.
    val itemGrids: List<@Composable () -> Unit> = listOf(
        {
            ItemGrid(
                stringResource(R.string.plastics),
                recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter { it.category == stringResource(
                    R.string.plastic
                ) }),
                viewModel,
                color = PlasticColor
            )
        },
        {
            ItemGrid(
                stringResource(R.string.metals),
                recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter { it.category == stringResource(
                    R.string.metal
                ) }),
                viewModel,
                color = MetalColor
            )
        },
        {
            ItemGrid(
                stringResource(R.string.glass),
                recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter { it.category == stringResource(
                    id = R.string.glass
                ) }),
                viewModel,
                color = GlassColor
            )
        },
        {
            ItemGrid(
                stringResource(R.string.cardboard),
                recyclableItems = mutableStateOf(viewModel.uiState.value.itemCounts.value.filter { it.category == stringResource(
                    id = R.string.cardboard
                ) }),
                viewModel,
                color = CardboardColor
            )
        }
    )

    // Lazy Column to invoke Composables to create item grid UI
            LazyColumn {
                for (grid in itemGrids) {
                    item {
                        grid.invoke()
                    }
                }
            }
        }




