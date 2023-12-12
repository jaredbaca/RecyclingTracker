package edu.bu.recyclingtracker.data

import androidx.compose.runtime.mutableStateOf
import edu.bu.recyclingtracker.R

/*
List of RecyclingItemUiState objects for each recyclable item.
This serves as the master list of all recyclables available in the app.
Any new items should be added here first.
 */
val recyclables = mutableStateOf( listOf(
    //Plastics
    RecyclingItemUiState("12oz Bottle", "Plastic" ,icon = R.drawable.plastic_bottle),
    RecyclingItemUiState("2-Liter Bottle", "Plastic", icon = R.drawable.two_liter_bottle),
    RecyclingItemUiState("Takeout Box", "Plastic", icon = R.drawable.takeout_container),
    RecyclingItemUiState("Detergent Bottle", "Plastic", icon = R.drawable.detergent_bottle),
    RecyclingItemUiState("Milk Jug", "Plastic", icon = R.drawable.detergent_bottle),

    //Metals
    RecyclingItemUiState("Aluminum Can", "Metal", icon = R.drawable.aluminum_can),
    RecyclingItemUiState("Steel Can", "Metal",  icon = R.drawable.steel_can),

    //Glass
    RecyclingItemUiState("Beer Bottle", "Glass", icon = R.drawable.glass_bottle),
    RecyclingItemUiState("Wine Bottle", "Glass", icon = R.drawable.wine_bottle),
    RecyclingItemUiState("Mason Jar", "Glass", icon = R.drawable.glass_jar),
    RecyclingItemUiState("Pasta Sauce Jar", "Glass", icon = R.drawable.glass_jar),

    //Cardboard
    RecyclingItemUiState("Small Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Medium Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Large Box", "Cardboard", icon = R.drawable.cardboard_box),
    RecyclingItemUiState("Pizza Box", "Cardboard", icon = R.drawable.pizza_box),
    RecyclingItemUiState("Frozen Food Box", "Cardboard", icon = R.drawable.frozen_food_box),
))

/*
Master list of item weight estimates.
Weight amounts are very rough and are only intended to provide a rough estimate.
 */
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

// Temporary current user constant for testing. Will be replace by Firebase auth instance.
const val CURRENT_USER = "Jared Baca"
