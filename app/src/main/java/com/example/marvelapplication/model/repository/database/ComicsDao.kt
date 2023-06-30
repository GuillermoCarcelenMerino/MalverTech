package com.example.marvelapplication.model.repository.database

import androidx.room.*
import com.example.marvelapplication.model.comics.MarvelComic

@Dao
interface ComicsDao {
    @Query("SELECT * FROM comics WHERE id >= :start AND id <= :end")
    suspend fun getComicsInRange(start: Int, end: Int): List<MarvelComic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MarvelComic>)
}
