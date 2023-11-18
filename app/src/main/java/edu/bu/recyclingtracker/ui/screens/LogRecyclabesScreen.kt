package edu.bu.recyclingtracker.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.bu.recyclingtracker.R
import edu.bu.recyclingtracker.RecyclableItemViewModel
import edu.bu.recyclingtracker.data.recyclables
import edu.bu.recyclingtracker.ui.LogRecyclablesViewModel
import edu.bu.recyclingtracker.ui.components.ItemCard

val icons = listOf(
    R.drawable.aluminum_can,
    R.drawable.glass_bottle,
    R.drawable.plastic_bottle,
)

//val recyclables = listOf(
//    "Aluminum Can",
//    "Glass Bottle",
//    "Plastic Bottle"
//)

var viewModel = LogRecyclablesViewModel()

@Composable
fun LogRecyclablesScreen(model: LogRecyclablesViewModel) {

    var selectedState by remember { mutableStateOf(
        false
    ) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            text = Routes.LOG_RECYCLABLES_SCREEN
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp), content = {
                items(icons.size) { index ->
//                    selectedState = model.itemList.contains(recyclables[index])

                    ItemCard(image = icons[index],
                            selected = false,
                            name = recyclables[index],
                            itemViewModel = RecyclableItemViewModel(
                                name = recyclables[index]
                            ),
//                            itemSelected = {
//                                model.addItem(it)
//                                model.onEvent(
//                                    UserDataUiEvents.itemSelected(it)
//                                )
//                            }
                            )
                    }
                }
            )
        }
    }
}

//@Preview
//@Composable
//fun LogRecyclablesScreenPreview() {
//    LogRecyclablesScreen(model = LogRecyclablesViewModel(), navHostController = rememberNavController())
//}