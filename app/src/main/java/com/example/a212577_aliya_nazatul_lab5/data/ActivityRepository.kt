package com.example.a212577_aliya_nazatul_lab5.data

class ActivityRepository(private val dao: ActivityDao) {

    val allActivities = dao.getAllActivities()

    suspend fun insert(activity: ActivityEntity) {
        dao.insert(activity)
    }

    suspend fun delete(activity: ActivityEntity) {
        dao.delete(activity)
    }
}