package edu.bu.recyclingtracker.data

import java.util.Date

data class Entry(
    var date: Date,
    var items: MutableMap<String, Int>
)
