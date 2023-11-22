package edu.bu.recyclingtracker.data

import java.util.Date

data class Entry(
    var date: Date,
    var items: MutableMap<String, Int>
)

//data class Entry(
//    var date: Date,
//    var aluminumCan: Int,
//    var glassBottle: Int,
//    var plasticBottle: Int
//)
