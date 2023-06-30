package com.example.marvelapplication.model.repository.database

import androidx.room.*
import com.example.marvelapplication.model.events.MarvelEvent

@Dao
interface EventsDao {
    @Query("SELECT * FROM events WHERE id >= :start AND id <= :end")
    suspend fun getEventsInRange(start: Int, end: Int): List<MarvelEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MarvelEvent>)
}
