package com.example.marvelapplication.paging

import com.example.marvelapplication.repository.EventsRepository

class EventsPaging(
    private val repository: EventsRepository,
    override val fromDB: Boolean,
) :
    BasicPaging<EventsRepository>(repository, fromDB)
