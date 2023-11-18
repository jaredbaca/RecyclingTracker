package edu.bu.recyclingtracker.data

import java.util.Date

data class Bin(
    var date: Date,
    var items: MutableMap<String, Int>
)
