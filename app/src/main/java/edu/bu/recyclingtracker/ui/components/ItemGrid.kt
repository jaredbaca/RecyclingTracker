package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.recyclingtracker.ui.screens.viewmodels.RecyclingItemUiState
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel

/*
Item Grid composable creates a grid of Item Cards to display available recycling items.
 */
@Composable
fun ItemGrid(title:String,
             recyclableItems:State<List<RecyclingItemUiState>>,
             viewModel: LogRecyclablesViewModel,
             color: Color
) {

    val uiState = viewModel.uiState

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Divider()
        Text(
            text = title, //Category Name
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
        )

        // Splits the list of items into groups of 2 to create columns on the home screen
        var chunkedItems = recyclableItems.value.chunked(2)

        chunkedItems.forEach { subList ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                subList.forEach { item ->
                    Column(verticalArrangement = Arrangement.Center) {
                        ItemCard(
                        selected = false,
                        name = item.name,
                        itemUiState = viewModel.uiState.value.itemCounts.value.find { it.name == item.name }
                            ?: RecyclingItemUiState("empty", "default category"),
                        viewModel,
                        0,
                        counterVisible = item.quantity > 0,
                        color = color
                    )
                    }
                }
            }
        }
    }
}
