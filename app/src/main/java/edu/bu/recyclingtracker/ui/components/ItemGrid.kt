package edu.bu.recyclingtracker.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.recyclingtracker.RecyclableItemViewModel
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

@Composable
fun ItemGrid(icons:List<Int>, title:String, recyclableItems:List<String>, viewModel: LogRecyclablesViewModel) {

    val uiState = viewModel.uiState

    Column {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()

        )
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp), content = {
            items(icons.size) { index ->

                ItemCard(image = icons[index],
                    selected = false,
                    name = recyclableItems[index],
                    itemUiState = uiState.value.itemCounts.value[index],
                    viewModel,
                    index
                )
            }
        }
        )
        Divider()
    }



}
