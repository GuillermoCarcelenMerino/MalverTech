package com.example.marvelapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.marvelapplication.model.repository.CharactersRepository
import com.example.marvelapplication.model.repository.ComicsRepository
import com.example.marvelapplication.model.repository.EventsRepository
import com.example.marvelapplication.paging.BasicPaging
import com.example.marvelapplication.paging.CharactersPaging
import com.example.marvelapplication.paging.ComicsPaging
import com.example.marvelapplication.paging.EventsPaging

open class BasicViewModel<T>(private val repository: T) :
    ViewModel() {

    fun createPager(fromDB: Boolean) = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 10),
    ) {
        when (repository) {
            is CharactersRepository -> CharactersPaging(repository, fromDB)
            is EventsRepository -> EventsPaging(repository, fromDB)
            is ComicsRepository -> ComicsPaging(repository, fromDB)
            else -> BasicPaging(repository, fromDB)
        }
    }.flow.cachedIn(viewModelScope)
}
