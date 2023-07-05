package com.example.marvelapplication.repository.database

import androidx.room.*
import com.example.marvelapplication.repository.entities.CharacterEntity

@Dao
interface CharactersDao {
    @Query("SELECT * FROM characters WHERE id >= :start AND id <= :end")
    suspend fun getCharactersInRange(start: Int, end: Int): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)
}
