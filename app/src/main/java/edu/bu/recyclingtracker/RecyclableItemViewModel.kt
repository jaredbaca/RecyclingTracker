package edu.bu.recyclingtracker

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState

class RecyclableItemViewModel(name: String,
                              quantity: Int = 0,
                              isSelected: Boolean = false) : ViewModel() {

    private val _itemInfo: MutableState<RecyclingItemUiState> = mutableStateOf(
        RecyclingItemUiState(
            name = name,
            quantity = quantity,
            isSelected = isSelected
        )
    )

    val itemInfo: MutableState<RecyclingItemUiState> get() = _itemInfo

    fun updateItemInfo(newItemInfo: RecyclingItemUiState) {
        _itemInfo.value = newItemInfo
    }

    fun incrementCount() {
        _itemInfo.value = _itemInfo.value.copy(quantity = itemInfo.value.quantity+1)
    }

    fun decrementCount() {
        if(_itemInfo.value.quantity > 0)
            _itemInfo.value = _itemInfo.value.copy(quantity = itemInfo.value.quantity-1)
    }
}


