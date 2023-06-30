package com.example.marvelapplication.model.repository

import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.model.repository.database.ComicsDao
import com.example.marvelapplication.model.repository.network.MarvelApi
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

    suspend fun insertAll(comics: List<MarvelComic>) = dao.insertAll(comics)
}
