package com.example.marvelapplication.ui.comics.viewmodel

import com.example.marvelapplication.model.repository.ComicsRepository
import com.example.marvelapplication.ui.BasicViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val repository: ComicsRepository) :
    BasicViewModel<ComicsRepository>(repository)
