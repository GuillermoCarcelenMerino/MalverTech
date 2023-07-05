package com.example.marvelapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.repository.CharactersRepository
import com.example.marvelapplication.repository.ComicsRepository
import com.example.marvelapplication.repository.EventsRepository
import com.example.marvelapplication.repository.network.ApiResponse
import com.example.marvelapplication.utils.*
import retrofit2.HttpException

open class BasicPaging<T>(private val repository: T, open val fromDB: Boolean) :
    PagingSource<Int, MarvelCharacter>() {

    private val offsetCons = 20
    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(offsetCons) ?: anchorPage?.nextKey?.minus(offsetCons)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        return try {
            val offset = params.key ?: 0
            if (fromDB) {
                val data = loadFromDB(offset)
                LoadResult.Page(
                    data = data.toMutableList(),
                    prevKey = if (offset > 0) offset - offsetCons else null,
                    nextKey = offset.plus(offsetCons),
                )
            } else {
                val load = loadFromRepo(offset)
                if (load is LoadResult.Page) {
                    return load
                } else {
                    when (repository) {
                        is EventsRepository -> LoadResult.Error(MarvelExceptionEvent())
                        is ComicsRepository -> LoadResult.Error(ComicsException())
                        is CharactersRepository -> LoadResult.Error(CharactersException())
                        else -> LoadResult.Error(Exception("Abnormal Error"))
                    }
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

    private suspend fun loadFromRepo(offset: Int): LoadResult<Int, MarvelCharacter> {
        val generator = MarvelRequestGenerator.createParams()
        val response = when (repository) {
            is EventsRepository -> repository.getData(
                generator.timestamp,
                generator.apiKey,
                generator.hash,
                offsetCons,
                offset,
            )

            is ComicsRepository -> repository.getData(
                generator.timestamp,
                generator.apiKey,
                generator.hash,
                offsetCons,
                offset,
            )

            is CharactersRepository -> repository.getData(
                generator.timestamp,
                generator.apiKey,
                generator.hash,
                offsetCons,
                offset,
            )
            else -> null
        }

        return if (response?.isSuccessful == true) {
            getData(response.body()!!, offset)
        } else {
            when (repository) {
                is EventsRepository -> LoadResult.Error(MarvelExceptionEvent())

                is ComicsRepository -> LoadResult.Error(ComicsException())

                is CharactersRepository -> LoadResult.Error(CharactersException())
                else -> LoadResult.Error(Throwable("Unkwon exception"))
            }
        }
    }

    private suspend fun getData(body: Any, offset: Int): LoadResult.Page<Int, MarvelCharacter> {
        var mappedDatta: List<MarvelCharacter> = mutableListOf()
        when (body) {
            is ApiResponse.EventResponseOk -> {
                mappedDatta = body.data.results?.toMarvelCharacterEvent() ?: mutableListOf()
                if (body.data.results?.isNotEmpty() == true) {
                    (repository as EventsRepository).insertAll(body.data.results!!.map { it.toEntity() })
                }
            }

            is ApiResponse.ComicResponseOk -> {
                mappedDatta = body.data.results?.toMarvelCharacter() ?: mutableListOf()
                if (body.data.results?.isNotEmpty() == true) {
                    (repository as ComicsRepository).insertAll(body.data.results!!.map { it.toEntity() })
                }
            }

            is ApiResponse.CharacterResponseOk -> {
                mappedDatta = body.data.results ?: mutableListOf()
                if (body.data.results?.isNotEmpty() == true) {
                    (repository as CharactersRepository).insertAll(
                        body.data.results!!.map {
                            it.toEntity()
                        },
                    )
                }
            }
            else -> {}
        }

        return LoadResult.Page(
            data = mappedDatta.toMutableList(),
            prevKey = if (offset > 0) offset - offsetCons else null,
            nextKey = offset.plus(offsetCons),
        )
    }

    private suspend fun loadFromDB(offset: Int): List<MarvelCharacter> {
        return when (repository) {
            is EventsRepository -> repository.getEventsInRangeDB(offset, offset + offsetCons)
                .fromEntityToCharacterEvent()
            is ComicsRepository -> repository.getComicsDB(offset, offset + offsetCons)
                .fromEntityToCharacter()
            is CharactersRepository -> repository.getCharactersDB(offset, offset + offsetCons)
                .fromEntity()
            else -> emptyList()
        }
    }
}
