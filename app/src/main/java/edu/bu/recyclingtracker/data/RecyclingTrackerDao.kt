package edu.bu.recyclingtracker.data

import android.util.Log
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import edu.bu.recyclingtracker.authentication.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import java.lang.Double.sum
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.typeOf

/*
Database Access Object that defines the implementation for functions interacting with Firestore DB.
 */
class RecyclingTrackerDao(private val firestore: FirebaseFirestore, private val loginViewModel: LoginViewModel) {

    /*
    Adds a new entry to the Firestore DB
     */
    fun addEntry(entry: Entry) {
        try {
//            entriesCollectionReference.add(entry)
            firestore.collection("users/${loginViewModel.currentUser.value?.email}/entries").add(entry)
            Log.d("Auth", "Current User in AddEntry: ${loginViewModel.currentUser.value?.email}")
        } catch (e: Exception) {
            Log.w(TAG, "Error adding user to db")
        }
    }

    /*
    Manually updates the totals document in the Firestore DB. Used whenever a new entry is added to make sure the totals
    are in sync with the entries collection.
    This is a manual implementation of something that could be done with cloud functions in a future iteration.
     */
    suspend fun updateTotals(currentTotals: Map<String, Any>, updates: Map<String, Any>) = suspendCoroutine { continuation ->
        try {
            var newTotals = currentTotals.toMutableMap()

            Log.d(TAG, "new totals: ${newTotals.toString()}, current totals: ${currentTotals.toString()}, updates: ${updates.toString()}")

            // Adds the updated amount (from viewModel counts) to the previous total
            for(key in updates.keys) {
                newTotals[key] = sum((currentTotals[key]?:0).toString().toDouble(),
                    updates[key].toString().toDouble())
            }
            // Replace existing totals in Firestore DB with newly updated totals
            firestore.collection("users/${loginViewModel.currentUser.value?.email}/totals").document("totals").set(newTotals)
            continuation.resume(Unit)
        } catch (e: Exception) {
            Log.w(TAG, "Error updating totals")
            continuation.resume(Unit)
        }
    }

    /*
    Retrieve the item totals from Firestore DB. Returns a map of <String, Any>
     */
    suspend fun getTotals(): Map<String, Any> = suspendCoroutine { continuation ->
        try {

            val documentRef = firestore.collection("users/${loginViewModel.currentUser.value?.email}/totals").document("totals")

            documentRef.get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val totals = document.data ?: emptyMap()
                        Log.d(TAG, "Totals: ${totals.toString()}")
                        continuation.resume(totals)
                    } else {
                        Log.w(TAG, "Document not found")
                        firestore.document("users/${loginViewModel.currentUser.value?.email}/totals/totals").set(emptyMap<String, Any>())
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