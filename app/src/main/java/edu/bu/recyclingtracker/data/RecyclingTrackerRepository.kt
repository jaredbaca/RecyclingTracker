package edu.bu.recyclingtracker.data

class RecyclingTrackerRepository(private val recyclingDao: RecyclingTrackerDao) {

    fun addUser(user: HashMap<String, String>) {
        recyclingDao.addUser(user)
    }

    fun addEntry(entry: Entry) {
        recyclingDao.addEntry(entry)
    }
}