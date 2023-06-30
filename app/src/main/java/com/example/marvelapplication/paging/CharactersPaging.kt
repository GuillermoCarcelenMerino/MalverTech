package com.example.marvelapplication.paging

import com.example.marvelapplication.model.repository.CharactersRepository

class CharactersPaging(
    private val repository: CharactersRepository,
    override val fromDB: Boolean,
) :
    BasicPaging<CharactersRepository>(repository, fromDB)