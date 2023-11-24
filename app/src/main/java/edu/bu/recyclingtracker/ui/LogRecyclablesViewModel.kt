package edu.bu.recyclingtracker.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import androidx.compose.runtime.State
import edu.bu.recyclingtracker.data.Entry
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

    var totals: MutableState<MutableMap<String, Any>> = mutableStateOf(getItemNames().associateWith { 0 }.toMutableMap())

    //Updates totals from ViewModel (not DB). Unnecessary once DB is implemented
    suspend fun updateTotals() {
//        uiState.value.itemCounts.value.forEach {
//            totals[it.name] = totals[it.name]!! + it.quantity
//        }
        totals.value = getTotalsFromDB().toMutableMap()
    }

//    fun calculatePercentages() {
//
//        //Calculate material totals
//        var plastics = totals.value.keys.filter { it ==  }
//    }


//    If DB objects store a map
    suspend fun addEntryFromCurrentBin() {

        //Get current counts from uiState
        var updates = uiState.value.itemCounts.value.associate { it.name to it.quantity}
            .toMutableMap()

        //Retrieve current totals from DB
        var currentTotals = repository.getTotals()

        //Add the current count to current totals
        repository.updateTotals(currentTotals, updates)

        //Replace totals
        repository.addEntry(Entry(Date(),
            updates)
        )
    }

//    //If DB objects store only fields
//    fun addEntryFromCurrentBin() {
//        val itemCountsMap = uiState.value.itemCounts.value.associate { it.name to it.quantity}.toMutableMap()
//        repository.addEntry(Entry(Date(),
//            aluminumCan = itemCountsMap["Aluminum Can"]!!,
//            glassBottle = itemCountsMap["Glass Bottle"]!!,
//            plasticBottle = itemCountsMap["Plastic Bottle"]!!
//        ))
//    }

    suspend fun getTotalsFromDB() : Map<String, Any>{
        return repository.getTotals()
    }

    suspend fun resetCounts() {
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
}