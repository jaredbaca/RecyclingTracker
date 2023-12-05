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
    RecyclingItemUiState("Beer Bottle", "Glass", icon = R.drawable.glass_bottle_white),
    RecyclingItemUiState("Wine Bottle", "Glass", icon = R.drawable.glass_bottle_white),
    RecyclingItemUiState("Mason Jar", "Glass", icon = R.drawable.glass_jar_white),
    RecyclingItemUiState("Pasta Sauce Jar", "Glass", icon = R.drawable.glass_jar_white),

    //Cardboard
    RecyclingItemUiState("Small Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Medium Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Large Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Pizza Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Frozen Food Box", "Cardboard", icon = R.drawable.frozen_food_box),
))

val itemWeights: Map<String, Int> = mapOf(
    //Weight in grams
    //Plastics
    "12oz Bottle" to 15,
    "2-Liter Bottle" to 40,
    "Takeout Box" to 15,
    "Detergent Bottle" to 200,
    "Milk Jug" to 110,

    //Metals
    "Aluminum Can" to 13,
    "Steel Can" to 35,

    //Glass
    "Beer Bottle" to 250,
    "Wine Bottle" to 600,
    "Mason Jar" to 350,
    "Pasta Sauce Jar" to 250,

    //Cardboard
    "Small Box" to 300,
    "Medium Box" to 600,
    "Large Box" to 1300,
    "Pizza Box" to 400,
    "Frozen Food Box" to 300
)


val icons = listOf(
    R.drawable.aluminum_can,
    R.drawable.glass_bottle,
    R.drawable.plastic_bottle,
)

const val CURRENT_USER = "Jared Baca"
