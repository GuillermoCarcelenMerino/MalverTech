package com.example.marvelapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.marvelapplication.model.Thumbnail
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.characters.MarvelCharacterData
import com.example.marvelapplication.model.repository.CharactersRepository
import com.example.marvelapplication.model.repository.network.ApiResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class BasicViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var charactersRepository: CharactersRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test // Is the same for all repos as they do extend from BasicViewModel. It only change in the repo used
    fun addMarvelCharacter() {
        runBlocking {
            val basicViewModel = BasicViewModel(charactersRepository)
            val response: Response<ApiResponse.CharacterResponseOk> = Response.success(
                ApiResponse.CharacterResponseOk(
                    data = MarvelCharacterData(listOf(MarvelCharacter(0, "", Thumbnail(), ""))),
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

            val pager = basicViewModel.createPager(fromDB = true).first()

            assertThat(pager, instanceOf(PagingData::class.java))
        }
    }
}
