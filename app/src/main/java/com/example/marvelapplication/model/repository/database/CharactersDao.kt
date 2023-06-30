package com.example.marvelapplication.model.repository.database

import androidx.room.*
import com.example.marvelapplication.model.characters.MarvelCharacter

@Dao
interface CharactersDao {
    @Query("SELECT * FROM characters WHERE id >= :start AND id <= :end")
    suspend fun getCharactersInRange(start: Int, end: Int): List<MarvelCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<MarvelCharacter>)
}
