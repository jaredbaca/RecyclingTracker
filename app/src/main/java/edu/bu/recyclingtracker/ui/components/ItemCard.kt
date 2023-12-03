package edu.bu.recyclingtracker.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import edu.bu.recyclingtracker.R
import edu.bu.recyclingtracker.RecyclableItemViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.UserDataUiEvents
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
//import edu.bu.recyclingtracker.ui.screens.viewModel

@Composable
fun ItemCard(
//            image:Int?,
             selected:Boolean,
             name:String,
             itemUiState: RecyclingItemUiState,
             viewModel: LogRecyclablesViewModel,
             index: Int,
             counterVisible: Boolean,
             color: Color
             )
{
    Box(Modifier
//        .border(BorderStroke(2.dp, Color.Black))
        .size(150.dp, 130.dp),
        contentAlignment = Alignment.Center
    ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(64.dp),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        top = 24.dp,
                        end = 24.dp
                    )
                    .size(60.dp)
                    .clickable {
                        viewModel.incrementCount(name)
                        Log.d("Count", viewModel.uiState.value.itemCounts.value.toString())
//                    Log.d("Count", name)
                    },
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
//                    .border(
//                        width = 1.dp,
//                        color = if (itemUiState.quantity > 0) Color.Green else Color.Transparent,
//                        shape = RoundedCornerShape(8.dp),
//                    )
                    ,
                    contentAlignment = Alignment.Center

                ) {
                    if (itemUiState.icon != null) {
                        Image(
                            modifier = Modifier
//                            .fillMaxSize()
                                .size(40.dp),
                            painter = painterResource(id = itemUiState.icon!!),
                            contentDescription = "Box Icon",
                        )
                    }
                }
            }
            Text(text = name, textAlign = TextAlign.Center)
            Counter(
                viewModel = viewModel,
                itemUiState,
                visible = counterVisible,
                name, index
            )
        }
    }
}

@Composable
fun ItemCardNoCounter(
//            image:Int?,
    selected:Boolean,
    name:String,
    itemUiState: RecyclingItemUiState,
    viewModel: LogRecyclablesViewModel,
    index: Int,
    color: Color
)
{
    Box(Modifier
//        .border(BorderStroke(2.dp, Color.Black))
        .size(150.dp, 130.dp),
        contentAlignment = Alignment.Center
    ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(64.dp),
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        top = 24.dp,
                        end = 24.dp
                    )
                    .size(60.dp)
                    .clickable {
                        viewModel.incrementCount(name)
                        Log.d("Count", viewModel.uiState.value.itemCounts.value.toString())
//                    Log.d("Count", name)
                    },
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
//                    .border(
//                        width = 1.dp,
//                        color = if (itemUiState.quantity > 0) Color.Green else Color.Transparent,
//                        shape = RoundedCornerShape(8.dp),
//                    )
                    ,
                    contentAlignment = Alignment.Center

                ) {
                    if (itemUiState.icon != null) {
                        Image(
                            modifier = Modifier
//                            .fillMaxSize()
                                .size(40.dp),
                            painter = painterResource(id = itemUiState.icon!!),
                            contentDescription = "Box Icon",
                        )
                    }
                }
            }
            Text(text = name, textAlign = TextAlign.Center)
        }
    }
}