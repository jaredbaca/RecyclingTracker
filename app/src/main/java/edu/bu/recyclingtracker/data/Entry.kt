package edu.bu.recyclingtracker.data

import java.util.Date

/*
Data class to hold recycled items and date stamp for submission to Firestore DB.
 */
data class Entry(
    var date: Date,
    var items: MutableMap<String, Int>
)
