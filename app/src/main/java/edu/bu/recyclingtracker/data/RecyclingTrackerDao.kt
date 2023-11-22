package edu.bu.recyclingtracker.data

import android.util.Log
import androidx.compose.runtime.currentCompositionErrors
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import java.lang.Double.sum
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.typeOf

class RecyclingTrackerDao(private val firestore: FirebaseFirestore) {

    val collectionReference = firestore.collection("users/${CURRENT_USER}/entries")

    //Test function - need to rewrite for recyclables instead of users
    fun addUser(user: HashMap<String, String>) {
        try {
            firestore.collection("users").add(user)
        } catch (e: Exception) {
            Log.w("Firebase", "Error adding user to db")
        }
    }

    fun addEntry(entry: Entry) {
        try {
            firestore.collection("users/${CURRENT_USER}/entries").add(entry)
        } catch (e: Exception) {
            Log.w("Firestore", "Error adding user to db")
        }
    }

    fun updateTotals(currentTotals: Map<String, Any>, updates: Map<String, Any>) {
        try {
            var newTotals = currentTotals.toMutableMap()
            Log.d(TAG, currentTotals.keys.toString())
            Log.d(TAG, currentTotals.values.toString())
            Log.d(TAG, currentTotals.containsKey("Plastic Bottle").toString())
//            Log.d(TAG, "new totals: ${newTotals.toString()}, current totals: ${currentTotals.toString()}, updates: ${updates.toString()}")
            for(key in updates.keys) {
                Log.d(TAG, newTotals.containsKey(key).toString())
                newTotals[key] = sum(currentTotals[key].toString().toDouble(),
                    updates[key].toString().toDouble())
            }
            firestore.collection("users/${CURRENT_USER}/totals").document("totals").set(newTotals)
        } catch (e: Exception) {
            Log.w("Firestore", "Error updating totals")
        }
    }

    suspend fun getTotals(): Map<String, Any> = suspendCoroutine { continuation ->
        try {
            val documentRef = firestore.collection("users/${CURRENT_USER}/totals").document("totals")

            documentRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val totals = document.data ?: emptyMap()
                        Log.d(TAG, "Totals: ${totals.toString()}")
                        continuation.resume(totals)
                    } else {
                        Log.w(TAG, "Document not found")
                        continuation.resume(emptyMap())
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Exception with totals retrieval", exception)
                    continuation.resume(emptyMap())
                }
        } catch (e: Exception) {
            Log.w(TAG, "Error getting totals", e)
            continuation.resume(emptyMap())
        }
    }
}

val TAG = "firestore"