package com.example.marvelapplication.ui.events.viewmodel

import com.example.marvelapplication.repository.EventsRepository
import com.example.marvelapplication.ui.BasicViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: EventsRepository) :
    BasicViewModel<EventsRepository>(repository)
