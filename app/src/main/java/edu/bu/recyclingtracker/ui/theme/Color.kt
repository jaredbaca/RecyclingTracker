package edu.bu.recyclingtracker.ui.theme

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//Item Specific Colors
val MetalColor = Color(parseColor("#A9B7C2"))
val PlasticColor = Color(parseColor("#81ABBC"))
val CardboardColor = Color(parseColor("#F7E2C7"))
val GlassColor = Color(parseColor("#29660C"))

//
val FABColor = Color(parseColor("#76B947"))

var categoryColors = mapOf<String, Color>(
    "Metal" to MetalColor,
    "Plastic" to PlasticColor,
    "Cardboard" to CardboardColor,
    "Glass" to GlassColor
)