package edu.bu.recyclingtracker.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.bu.recyclingtracker.data.RecyclingItemUiState
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import edu.bu.recyclingtracker.data.Entry
import edu.bu.recyclingtracker.data.RecyclingTrackerRepository
import edu.bu.recyclingtracker.data.itemWeights
import edu.bu.recyclingtracker.data.recyclables
import kotlinx.coroutines.launch
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
    var totalsByCategory: MutableState<MutableMap<String, Int>> = mutableStateOf(mutableMapOf())
    var weights: MutableState<MutableMap<String, Double>> = mutableStateOf( mutableMapOf())


    //Updates totals from ViewModel (not DB). Unnecessary once DB is implemented
    suspend fun updateTotals() {
//        uiState.value.itemCounts.value.forEach {
//            totals[it.name] = totals[it.name]!! + it.quantity
//        }
        totals.value = getTotalsFromDB().toMutableMap()
    }

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

    suspend fun getTotalsFromDB() : Map<String, Any>{
        return repository.getTotals()
    }

    suspend fun getTotalsByCategory(): MutableMap<String, Int> {

        //Get item totals
        viewModelScope.launch {val totals = getTotalsFromDB() }

        //Create map of categories/materials
//        var materials: MutableMap<String, MutableList<String>> = mutableMapOf()
        var categoryTotals = mutableMapOf<String, Int>()

        //Longer version
//        //Populate categories map with items from each category
//        uiState.value.itemCounts.value.forEach {
//            Log.d("Category", it.category)
//            if(!materials.containsKey(it.category)) {
//                materials.put(it.category, mutableListOf(it.name))
//            } else {
//                materials[it.category]!!.add(it.name)
//            }
//        }
//
//        for(category in materials.keys) {
//            var total = 0
//            var items = materials[category]
//            items!!.forEach { total += totals.value[it].toString().toDouble().toInt()}
//            categoryTotals.put(category, total)
//        }

        //Refactored version
        totals.value.forEach { total ->
            var key = uiState.value.itemCounts.value.find { item -> item.name == total.key }?.category
            if(key != null && !categoryTotals.containsKey(key)) {
                categoryTotals.put(key, total.value.toString().toDouble().toInt())
            } else if(key != null) {
                categoryTotals[key] = categoryTotals[key]!! + total.value.toString().toDouble().toInt()
            }
        }

//        Log.d("categories", materials.toString())
        Log.d("category totals", categoryTotals.toString())

        return categoryTotals
    }

    // Resets items counts in ViewModel
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

    fun updateItemQuantity(itemName: String, newQuantity: String) {
        val newItemCounts: MutableList<RecyclingItemUiState> = uiState.value.itemCounts.value.toMutableList()
        newItemCounts.forEach {
            if(it.name == itemName && newQuantity.toInt() >= 0)
                it.quantity = newQuantity.toInt()
        }
        uiState.value = uiState.value.copy(
            itemCounts = mutableStateOf(newItemCounts)
        )
    }

    fun gramsToPounds(grams: Double): Double {
        val poundsPerKilogram = 2.20462
        return grams / 1000 * poundsPerKilogram
    }

    suspend fun calculateWeights(): MutableMap<String, Double> {

        var newWeights: MutableMap<String, Double> =  mutableMapOf()

        //Calculate weights for individual items

        totals.value.forEach { total ->
            if(total.key != null) {
                newWeights.put(total.key,
                    gramsToPounds(total.value.toString().toDouble() * (itemWeights[total.key]?.toDouble()?:0.0)))
            }
        }

        //Calculate weights for categories
        var items = mutableListOf<String>()

        totalsByCategory.value.keys.forEach { category ->
            items.clear()
            uiState.value.itemCounts.value.forEach { if(it.category==category) items.add(it.name) }
            var sum = newWeights.filter {weight -> items.contains(weight.key) }.values.sum()
            newWeights.put(category, sum)
        }



        Log.d("New Weights", "weights: $newWeights")
        return newWeights
    }

}





