package com.example.marvelapplication.repository.database

import androidx.room.*
import com.example.marvelapplication.repository.entities.EventEntity

@Dao
interface EventsDao {
    @Query("SELECT * FROM events WHERE id >= :start AND id <= :end")
    suspend fun getEventsInRange(start: Int, end: Int): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<EventEntity>)
}
