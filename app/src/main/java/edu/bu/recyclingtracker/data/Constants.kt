package edu.bu.recyclingtracker.data

import androidx.compose.runtime.mutableStateOf
import edu.bu.recyclingtracker.R

//val recyclables = listOf(
//    "Aluminum Can",
//    "Glass Bottle",
//    "Plastic Bottle"
//)

val recyclables = mutableStateOf( listOf(
    RecyclingItemUiState("Aluminum Can"),
    RecyclingItemUiState("Glass Bottle"),
    RecyclingItemUiState("Plastic Bottle")
))

val icons = listOf(
    R.drawable.aluminum_can,
    R.drawable.glass_bottle,
    R.drawable.plastic_bottle,
)

const val CURRENT_USER = "Jared Baca"