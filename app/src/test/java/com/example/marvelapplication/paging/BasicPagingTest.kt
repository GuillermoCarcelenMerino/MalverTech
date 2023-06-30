package com.example.marvelapplication.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapplication.model.Thumbnail
import com.example.marvelapplication.model.characters.CharactersException
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.characters.MarvelCharacterData
import com.example.marvelapplication.model.repository.CharactersRepository
import com.example.marvelapplication.model.repository.network.ApiResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class BasicPagingTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun pagingData_load_from_repo_OK() {
        runBlocking {
            val marvelCharacter = MarvelCharacter(0, "", Thumbnail(), "")
            val response: Response<ApiResponse.CharacterResponseOk> = Response.success(
                ApiResponse.CharacterResponseOk(
                    data = MarvelCharacterData(listOf(marvelCharacter)),
                ),
            )
            Mockito.`when`(
                charactersRepository.getData(
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any(),
                    Mockito.any(),
                ),
            ).thenReturn(response)
            val pagingSource = CharactersPaging(charactersRepository, false)

            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(marvelCharacter),
                    prevKey = null,
                    nextKey = 20,
                ),
                actual = pagingSource.load(
                    Refresh(
                        key = null,
                        loadSize = 20,
                        placeholdersEnabled = false,
                    ),
                ),
            )
            Mockito.verify(charactersRepository).getData(
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
            )
        }
    }

    @Test
    fun pagingData_load_from_repo_error() = runTest {
        val response: Response<ApiResponse.CharacterResponseOk> = Response.error(
            404,
            ResponseBody.create(null, "Error on creation"),
        )
        Mockito.`when`(
            charactersRepository.getData(
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
                Mockito.any(),
            ),
        ).thenReturn(response)
        val pagingSource = CharactersPaging(charactersRepository, false)
        val pagingSourceData = pagingSource.load(
            Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )
        MatcherAssert.assertThat(
            pagingSourceData,
            CoreMatchers.instanceOf(PagingSource.LoadResult.Error::class.java),
        )

        MatcherAssert.assertThat(
            (pagingSourceData as PagingSource.LoadResult.Error<Int, MarvelCharacter>).throwable,
            CoreMatchers.instanceOf(CharactersException::class.java),
        )
        Mockito.verify(charactersRepository).getData(
            Mockito.any(),
            Mockito.any(),
            Mockito.any(),
            Mockito.any(),
            Mockito.any(),
        )
    }

    @Test
    fun pagingData_load_from_DB_OK() {
        runBlocking {
            val marvelCharacter = MarvelCharacter(0, "", Thumbnail(), "")
            Mockito.`when`(
                charactersRepository.getCharactersDB(
                    Mockito.anyInt(),
                    Mockito.anyInt(),
                ),
            ).thenReturn(listOf(marvelCharacter))

            val pagingSource = CharactersPaging(charactersRepository, true)

            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(marvelCharacter),
                    prevKey = null,
                    nextKey = 20,
                ),
                actual = pagingSource.load(
                    Refresh(
                        key = null,
                        loadSize = 20,
                        placeholdersEnabled = false,
                    ),
                ),
            )
            Mockito.verify(charactersRepository).getCharactersDB(
                Mockito.anyInt(),
                Mockito.anyInt(),
            )
        }
    }
}
