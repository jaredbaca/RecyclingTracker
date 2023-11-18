package edu.bu.recyclingtracker.data

sealed class UserDataUiEvents{
    data class itemSelected(val itemValue:String) : UserDataUiEvents()
}