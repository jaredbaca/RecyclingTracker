package edu.bu.recyclingtracker.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel

@Composable
fun ItemGrid(title:String,
             recyclableItems:State<List<RecyclingItemUiState>>,
             viewModel: LogRecyclablesViewModel,
             color: Color
) {

    val uiState = viewModel.uiState

//    Log.d("Item Grid", recyclableItems.toString())
//    Log.d("uiStates", uiState.value.itemCounts.value.toString())

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Divider()
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
//        CenteredDivider(110)




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
//            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp), content = {
////                item{Text("Plastics")}
//                items(recyclableItems.value.size) { index ->
////                    if(recyclableItems.value[index].category=="Plastic") {
//                        ItemCard(
////                    image = recyclableItems.value[index].icon,
//                            selected = false,
//                            name = recyclableItems.value[index].name,
//                            itemUiState = viewModel.uiState.value.itemCounts.value.find { it.name == recyclableItems.value[index].name }
//                                ?: RecyclingItemUiState("empty", "default category"),
//                            viewModel,
//                            index,
//                            counterVisible = viewModel.uiState.value.itemCounts.value[index].quantity > 0
//                        )
////                    }
//                }
//            }
//            )



    }

//    LazyColumn(
//        verticalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(24.dp)
//    ) {
//        item {
//            Text(text = "Plastics",
//            modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                fontSize = 24.sp
//            )
//        }
////        item { CenteredDivider() }
//        items(
//            items = recyclableItems.value.filter { it.category == "Plastic" }.chunked(2),
//            key = null
//        ) {
//            chunkedItems ->
//            LazyRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                items(chunkedItems) {
//                    item ->
//
//                    ItemCard(
//                        selected = false,
//                        name = item.name,
//                        itemUiState = viewModel.uiState.value.itemCounts.value.find { it.name == item.name }
//                            ?: RecyclingItemUiState("empty", "default category"),
//                        viewModel,
//                        0,
//                        counterVisible = item.quantity > 0
//                    )
//                }
//            }
//        }



//    }



}
