package com.example.marvelapplication.ui.details.viewmodel

import com.example.marvelapplication.model.repository.CharactersRepository
import com.example.marvelapplication.model.repository.ComicsRepository
import com.example.marvelapplication.ui.BasicViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: CharactersRepository) :
    BasicViewModel<CharactersRepository>(repository)
