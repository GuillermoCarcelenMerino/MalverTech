package com.example.marvelapplication.paging

import com.example.marvelapplication.repository.ComicsRepository

class ComicsPaging(
    private val repository: ComicsRepository,
    override val fromDB: Boolean,
) :
    BasicPaging<ComicsRepository>(repository, fromDB)
