package com.example.marvelapplication.repository

import com.example.marvelapplication.repository.database.EventsDao
import com.example.marvelapplication.repository.entities.EventEntity
import com.example.marvelapplication.repository.network.MarvelApi
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val api: MarvelApi,
    private val dao: EventsDao,
) {
    suspend fun getData(
        timestamp: Long?,
        apiKey: String?,
        hash: String?,
        limit: Int?,
        offset: Int?,
    ) = api.getEvents(timestamp, apiKey, hash, limit, offset)

    suspend fun getEventsInRangeDB(start: Int, end: Int) =
        dao.getEventsInRange(start, end)

    suspend fun insertAll(events: List<EventEntity>) = dao.insertAll(events)
}
