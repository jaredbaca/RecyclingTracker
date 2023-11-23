package edu.bu.recyclingtracker.data

import androidx.compose.runtime.mutableStateOf
import edu.bu.recyclingtracker.R

val recyclables = mutableStateOf( listOf(
    //Plastics
    RecyclingItemUiState("12oz Bottle", "Plastic" ,icon = R.drawable.plastic_bottle),
    RecyclingItemUiState("2-Liter Bottle", "Plastic", icon = R.drawable.plastic_bottle),
    RecyclingItemUiState("Takeout Box", "Plastic"),
    RecyclingItemUiState("Detergent Bottle", "Plastic", icon = R.drawable.detergent_bottle),
    RecyclingItemUiState("Milk Jug", "Plastic", icon = R.drawable.detergent_bottle),

    //Metals
    RecyclingItemUiState("Aluminum Can", "Metal", icon = R.drawable.aluminum_can),
    RecyclingItemUiState("Steel Can", "Metal",  icon = R.drawable.steel_can),

    //Glass
    RecyclingItemUiState("Beer Bottle", "Glass", icon = R.drawable.glass_bottle),
    RecyclingItemUiState("Wine Bottle", "Glass", icon = R.drawable.glass_bottle),
    RecyclingItemUiState("Mason Jar", "Glass", icon = R.drawable.glass_jar),
    RecyclingItemUiState("Pasta Sauce Jar", "Glass", icon = R.drawable.glass_jar),

    //Cardboard
    RecyclingItemUiState("Small Cardboard Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Medium Cardboard Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Large Cardboard Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Pizza Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Frozen Food Box", "Cardboard", icon = R.drawable.frozen_food_box),
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
