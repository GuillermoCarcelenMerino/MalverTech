package com.example.marvelapplication.repository

import com.example.marvelapplication.repository.database.ComicsDao
import com.example.marvelapplication.repository.entities.ComicEntity
import com.example.marvelapplication.repository.network.MarvelApi
import javax.inject.Inject

class ComicsRepository @Inject constructor(
    private val api: MarvelApi,
    private val dao: ComicsDao,
) {
    suspend fun getData(
        timestamp: Long?,
        apiKey: String?,
        hash: String?,
        limit: Int?,
        offset: Int?,
    ) = api.getComics(timestamp, apiKey, hash, limit, offset)

    suspend fun getComicsDB(start: Int, end: Int) =
        dao.getComicsInRange(start, end)

    suspend fun insertAll(comics: List<ComicEntity>) = dao.insertAll(comics)
}
