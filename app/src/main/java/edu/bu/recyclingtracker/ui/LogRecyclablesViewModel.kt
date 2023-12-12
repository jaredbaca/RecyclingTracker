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

/**
 * This ViewModel stores the current item counts for selected items and handles communication with the Firestore database
 */

class LogRecyclablesViewModel(private val repository: RecyclingTrackerRepository) : ViewModel() {
    data class RecyclingPageUiState(
        var itemCounts: State<List<RecyclingItemUiState>> = mutableStateOf( listOf())
    )

    var uiState = mutableStateOf(RecyclingPageUiState(recyclables))
    var totals: MutableState<MutableMap<String, Any>> = mutableStateOf(getItemNames().associateWith { 0 }.toMutableMap())
    var totalsByCategory: MutableState<MutableMap<String, Int>> = mutableStateOf(getTotalsByCategory())
    var weights: MutableState<MutableMap<String, Double>> = mutableStateOf( mutableMapOf())


    /*
     * Updates item totals held in ViewModel with totals from Firestore DB
    */
    suspend fun updateTotals() {
        totals.value = getTotalsFromDB().toMutableMap()
    }

    /*
    Creates a new Firestore entry from current item counts. Includes a date stamp and a map of recycled items
    */
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

    /*
    Implements repository function to retrieve totals from Firestore DB
     */
    suspend fun getTotalsFromDB() : Map<String, Any>{
        return repository.getTotals()
    }

    /*
     Returns a mutable map containing the item totals broken down by category: Plastic, Glass, Cardboard, Metal
     */
     fun getTotalsByCategory(): MutableMap<String, Int> {
        //Create map of categories
        var categoryTotals = mutableMapOf<String, Int>()

        //Calculate totals by category
        totals.value.forEach { total ->
            var key = uiState.value.itemCounts.value.find { item -> item.name == total.key }?.category
            if(key != null && !categoryTotals.containsKey(key)) {
                categoryTotals.put(key, total.value.toString().toDouble().toInt())
            } else if(key != null) {
                categoryTotals[key] = categoryTotals[key]!! + total.value.toString().toDouble().toInt()
            }
        }

        Log.d("category totals", categoryTotals.toString())

        return categoryTotals
    }

    /*
    Resets ViewModel item counts. Used after entry has been submitted to DB.
     */
    suspend fun resetCounts() {
        var newItemCounts = uiState.value.itemCounts.value.toMutableList()
        newItemCounts.forEach { it.quantity = 0 }

        uiState.value = uiState.value.copy(
            itemCounts = mutableStateOf( newItemCounts)
        )
    }

    /*
    Utility function to generate a list of item names from a list of RecyclingItemUiState objects
     */
    fun getItemNames() : MutableList<String> {
        var itemNames: MutableList<String> = mutableListOf()
        uiState.value.itemCounts.value.forEach { itemNames.add(it.name) }
        return itemNames
    }

    /*
    Increments item count in ViewModel
     */
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

    /*
    Decrements item count in ViewModel
     */
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

    /*
    Updates item count in ViewModel to a specified quantity
     */
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

    /*
    Utility function to convert grams to pounds for item weight calculations
     */
    fun gramsToPounds(grams: Double): Double {
        val poundsPerKilogram = 2.20462
        return grams / 1000 * poundsPerKilogram
    }

    /*
    Calculates the weights of all items currently logged in DB based on rough estimates of individual item weights.
     */
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





