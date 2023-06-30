package com.example.marvelapplication.model.repository

import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.repository.database.CharactersDao
import com.example.marvelapplication.model.repository.network.MarvelApi
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: MarvelApi,
    private val dao: CharactersDao,
) {
    suspend fun getData(
        timestamp: Long?,
        apiKey: String?,
        hash: String?,
        limit: Int?,
        offset: Int?,
    ) = api.getCharacters(timestamp, apiKey, hash, limit, offset)

    suspend fun getCharactersDB(start: Int, end: Int) =
        dao.getCharactersInRange(start, end)

    suspend fun insertAll(characters: List<MarvelCharacter>) = dao.insertAll(characters)
}
