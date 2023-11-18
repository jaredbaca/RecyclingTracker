package edu.bu.recyclingtracker.data

data class RecyclingItemUiState(
    val name: String,
//    val category: String,
    var isSelected: Boolean = false,
    var quantity: Int = 0
)