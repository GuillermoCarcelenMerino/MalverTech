package com.example.marvelapplication.ui.characters.viewmodel

import com.example.marvelapplication.repository.CharactersRepository
import com.example.marvelapplication.ui.BasicViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CharactersRepository) :
    BasicViewModel<CharactersRepository>(repository)
