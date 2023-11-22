package edu.bu.recyclingtracker.data

class RecyclingTrackerRepository(private val recyclingDao: RecyclingTrackerDao) {

    fun addUser(user: HashMap<String, String>) {
        recyclingDao.addUser(user)
    }

    fun addEntry(entry: Entry) {
        recyclingDao.addEntry(entry)
    }

    suspend fun getTotals() : Map<String, Any> {
        return recyclingDao.getTotals()
    }

    fun updateTotals(currentTotals: Map<String, Any>, updates: Map<String, Any>) {
        recyclingDao.updateTotals(currentTotals, updates)
    }
}