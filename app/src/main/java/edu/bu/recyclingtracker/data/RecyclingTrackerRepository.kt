package edu.bu.recyclingtracker.data

/*
Database Repository that calls the functions implemented in the DAO.
 */
class RecyclingTrackerRepository(private val recyclingDao: RecyclingTrackerDao) {

    fun addEntry(entry: Entry) {
        recyclingDao.addEntry(entry)
    }

    suspend fun getTotals() : Map<String, Any> {
        return recyclingDao.getTotals()
    }

    suspend fun updateTotals(currentTotals: Map<String, Any>, updates: Map<String, Any>) {
        recyclingDao.updateTotals(currentTotals, updates)
    }
}