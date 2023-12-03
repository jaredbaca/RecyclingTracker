package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.bu.recyclingtracker.RecyclableItemViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

@Composable
fun Counter(viewModel: LogRecyclablesViewModel,
            itemUiState: RecyclingItemUiState,
            visible:Boolean,
            name: String,
            index: Int
) {

//    if(visible) {
    Box(modifier = Modifier
//        .border(BorderStroke(2.dp, Color.Black))
        .size(100.dp, 40.dp)) {
        if(visible) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                IconButton(onClick = { viewModel.decrementCount(name) }) {
                    Icon(imageVector = Icons.Default.RemoveCircle, contentDescription = "")
                }
                Text(
                    text = itemUiState.quantity.toString(),
                    textAlign = TextAlign.Center,
                    color = if (visible) Color.Black else Color.Transparent
                )
                IconButton(onClick = { viewModel.incrementCount(name) }) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                }
            }
        }
    }

//    }

}