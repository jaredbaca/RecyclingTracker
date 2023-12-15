package edu.bu.recyclingtracker.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.bu.recyclingtracker.ui.screens.viewmodels.RecyclingItemUiState
import edu.bu.recyclingtracker.ui.screens.viewmodels.LogRecyclablesViewModel

/*
This composable creates the item card that displays each recycling item on the home screen.
Users click on the item card to add recyclables to their current Recycling Bin
 */
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
                    },
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                    ,
                    contentAlignment = Alignment.Center

                ) {
                    if (itemUiState.icon != null) {
                        Image(
                            modifier = Modifier
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

/*
A variant of the Item Card composable that does not include the counter. This is for use on the Bin Summary page,
where having a counter is not necessary and throws off the spacing.
 */
@Composable
fun ItemCardNoCounter(
    selected:Boolean,
    name:String,
    itemUiState: RecyclingItemUiState,
    viewModel: LogRecyclablesViewModel,
    index: Int,
    color: Color
)
{
    Box(Modifier
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
                    },
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                    ,
                    contentAlignment = Alignment.Center

                ) {
                    if (itemUiState.icon != null) {
                        Image(
                            modifier = Modifier
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