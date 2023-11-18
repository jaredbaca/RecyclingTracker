package edu.bu.recyclingtracker.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import edu.bu.recyclingtracker.RecyclableItemViewModel

@Composable
fun Counter(itemViewModel: RecyclableItemViewModel, visible:Boolean) {
    if(visible) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = {itemViewModel.decrementCount()}) {
                Icon(imageVector = Icons.Default.RemoveCircle, contentDescription = "")
            }
            Text(text = itemViewModel.itemInfo.value.quantity.toString(),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = {itemViewModel.incrementCount()}) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
            }
        }
    }

}