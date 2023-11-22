package edu.bu.recyclingtracker.data

import android.graphics.drawable.Drawable

data class RecyclingItemUiState(
    val name: String,
//    val category: String,
    var isSelected: Boolean = false,
    var quantity: Int = 0,
    var icon: Int? = null
)