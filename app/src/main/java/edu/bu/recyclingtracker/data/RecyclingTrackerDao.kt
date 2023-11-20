package edu.bu.recyclingtracker.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class RecyclingTrackerDao(private val firestore: FirebaseFirestore) {

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
}