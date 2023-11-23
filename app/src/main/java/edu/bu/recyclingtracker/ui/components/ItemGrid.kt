package edu.bu.recyclingtracker.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

@Composable
fun ItemGrid(title:String, recyclableItems:State<List<RecyclingItemUiState>>, viewModel: LogRecyclablesViewModel) {

    val uiState = viewModel.uiState

    Log.d("Item Grid", recyclableItems.toString())
    Log.d("uiStates", uiState.value.itemCounts.value.toString())

    Column {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()

        )
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp), content = {
            items(recyclableItems.value.size-1) { index ->

                ItemCard(
//                    image = recyclableItems.value[index].icon,
                    selected = false,
                    name = recyclableItems.value[index].name,
                    itemUiState = viewModel.uiState.value.itemCounts.value.random() ?: RecyclingItemUiState("empty"),
                    viewModel,
                    index
                )
            }
        }
        )
        CenteredDivider()
    }



}
