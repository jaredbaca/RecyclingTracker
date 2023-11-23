package edu.bu.recyclingtracker.data

import androidx.compose.runtime.mutableStateOf
import edu.bu.recyclingtracker.R

val recyclables = mutableStateOf( listOf(
    //Plastics
    RecyclingItemUiState("12oz Bottle", "Plastic" ,icon = R.drawable.plastic_bottle),
    RecyclingItemUiState("2-Liter Bottle", "Plastic"),
    RecyclingItemUiState("Takeout Box", "Plastic"),
    RecyclingItemUiState("Soap Bottles", "Plastic"),
    RecyclingItemUiState("Milk Jug", "Plastic"),

    //Metals
    RecyclingItemUiState("Aluminum Can", "Metal", icon = R.drawable.aluminum_can),
    RecyclingItemUiState("Soup Can", "Metal"),

    //Glass
    RecyclingItemUiState("Beer Bottle", "Glass", icon = R.drawable.glass_bottle),
    RecyclingItemUiState("Wine Bottle", "Glass"),
    RecyclingItemUiState("Mason Jar", "Glass"),
    RecyclingItemUiState("Pasta Sauce Jar", "Glass"),

    //Cardboard
    RecyclingItemUiState("Small Cardboard Box", "Cardboard"),
    RecyclingItemUiState("Medium Cardboard Box", "Cardboard"),
    RecyclingItemUiState("Large Cardboard Box", "Cardboard"),
    RecyclingItemUiState("Pizza Box", "Cardboard"),
    RecyclingItemUiState("Frozen Food Box", "Cardboard"),
))

//val metals = mutableStateOf( listOf(
//    RecyclingItemUiState("Aluminum Can", icon = R.drawable.aluminum_can),
//    RecyclingItemUiState("Soup Can", icon = R.drawable.aluminum_can)
//))

//val glass = mutableStateOf( listOf(
//    //Glass
//    RecyclingItemUiState("Glass Bottle", icon = R.drawable.glass_bottle),
//))

//val cardboard = mutableStateOf( listOf(
//    //Cardboard
//    RecyclingItemUiState("Small Cardboard Box")
//))


val icons = listOf(
    R.drawable.aluminum_can,
    R.drawable.glass_bottle,
    R.drawable.plastic_bottle,
)

const val CURRENT_USER = "Jared Baca"
