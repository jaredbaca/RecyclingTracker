package edu.bu.recyclingtracker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.data.UserDataUiEvents
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import edu.bu.recyclingtracker.data.recyclables

data class count(
    var items: MutableMap<String, Int>
)

class LogRecyclablesViewModel : ViewModel() {

    val uiState = mutableStateOf(RecyclingPageUiState())

    var _count by mutableStateOf(count(recyclables.associateWith { 0 }.toMutableMap()))

    fun updateCount(newCount: count) {
        _count.items = newCount.items
    }

//    val selectedItems = mutableListOf<String>()
    var itemList: MutableList<String> = mutableListOf()

    data class RecyclingPageUiState(
//        var selectedItems: List<String> = listOf(),
//        val selectedItem: String = "",
        var itemCounts: MutableList<RecyclingItemUiState> = mutableListOf()
    )

    var testString by mutableStateOf("test")

    fun updateTestString(newString: String) {
        testString = newString
    }


    fun addItem(item: String) {
        itemList.add(item)
    }

//    fun getSelectedItems(): List<String> {
//        return uiState.value.selectedItems
//    }


//    fun onEvent(event: UserDataUiEvents) {
//        when(event) {
//            is UserDataUiEvents.itemSelected -> {
//                uiState.value = uiState.value.copy(
//                    selectedItems = itemList
//                )
//                Log.d("TAG", getSelectedItems().toString())
//            }
//        }
//    }
}