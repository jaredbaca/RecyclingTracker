package edu.bu.recyclingtracker.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class RecylingTrackerDao(private val firestore: FirebaseFirestore) {

    fun addUser(user: HashMap<String, String>) {
        try {
            firestore.collection("users").add(user)
        } catch (e: Exception) {
            Log.w("Firebase", "Error adding user to db")
        }
    }
}