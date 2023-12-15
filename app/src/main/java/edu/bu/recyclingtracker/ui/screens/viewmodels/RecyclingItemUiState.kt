package edu.bu.recyclingtracker.ui.screens.viewmodels

import android.graphics.drawable.Drawable

/**
Data class to hold the uiState information for each recyclable item
 */

data class RecyclingItemUiState(
    val name: String,
    val category: String,
    var isSelected: Boolean = false,
    var quantity: Int = 0,
    var icon: Int? = null
)