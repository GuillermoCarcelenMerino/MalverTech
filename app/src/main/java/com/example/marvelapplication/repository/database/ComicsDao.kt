package com.example.marvelapplication.repository.database

import androidx.room.*
import com.example.marvelapplication.repository.entities.ComicEntity

@Dao
interface ComicsDao {
    @Query("SELECT * FROM comics WHERE id >= :start AND id <= :end")
    suspend fun getComicsInRange(start: Int, end: Int): List<ComicEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<ComicEntity>)
}
