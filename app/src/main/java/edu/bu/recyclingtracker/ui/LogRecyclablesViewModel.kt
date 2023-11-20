package edu.bu.recyclingtracker.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import edu.bu.recyclingtracker.data.UserDataUiEvents
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.bu.recyclingtracker.data.Entry
import edu.bu.recyclingtracker.data.RecyclingTrackerDao
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.recyclables
import java.util.Date

data class count(
    var items: MutableMap<String, Int>
)

class LogRecyclablesViewModel(private val repository: RecyclingTrackerRepository) : ViewModel() {

    data class RecyclingPageUiState(
        var itemCounts: State<List<RecyclingItemUiState>> = mutableStateOf( listOf())
    )

    /* TODO add immutable state and get method */
    var uiState = mutableStateOf(RecyclingPageUiState(recyclables))

    var totals: MutableMap<String, Int> = getItemNames().associateWith { 0 }.toMutableMap()

    fun updateTotals() {
        uiState.value.itemCounts.value.forEach {
            totals[it.name] = totals[it.name]!! + it.quantity
        }
    }

    fun addEntryFromCurrentBin() {
        repository.addEntry(Entry(Date(), uiState.value.itemCounts.value.associate { it.name to it.quantity}.toMutableMap()))
    }

    fun resetCounts() {
        uiState.value.itemCounts.value.forEach {
            it.quantity = 0
        }
    }

    fun getItemNames() : MutableList<String> {
        var itemNames: MutableList<String> = mutableListOf()
        uiState.value.itemCounts.value.forEach { itemNames.add(it.name) }
        return itemNames
    }

    fun incrementCount(itemName:String) {
        //Add exception here
        val newItemCounts: MutableList<RecyclingItemUiState> = uiState.value.itemCounts.value.toMutableList()
        newItemCounts.forEach {
            if(it.name == itemName)
                it.quantity = it.quantity+1
        }
        uiState.value = uiState.value.copy(
            itemCounts = mutableStateOf( newItemCounts)

        )
    }

    fun decrementCount(itemName:String) {
        //Add exception here
        val newItemCounts: MutableList<RecyclingItemUiState> = uiState.value.itemCounts.value.toMutableList()
        newItemCounts.forEach {
            if(it.name == itemName && it.quantity > 0)
                it.quantity = it.quantity-1
        }
        uiState.value = uiState.value.copy(
            itemCounts = mutableStateOf( newItemCounts)
        )
    }

//    var itemList: MutableList<String> = mutableListOf()

//    fun addItem(item: String) {
//        itemList.add(item)
//    }
}