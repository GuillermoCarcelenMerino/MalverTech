package com.example.marvelapplication.model.repository

import com.example.marvelapplication.model.events.MarvelEvent
import com.example.marvelapplication.model.repository.database.EventsDao
import com.example.marvelapplication.model.repository.network.MarvelApi
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

    suspend fun insertAll(events: List<MarvelEvent>) = dao.insertAll(events)
}
