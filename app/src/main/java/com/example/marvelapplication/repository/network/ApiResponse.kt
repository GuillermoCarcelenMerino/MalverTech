package com.example.marvelapplication.repository.network

import com.example.marvelapplication.model.characters.MarvelCharacterData
import com.example.marvelapplication.model.comics.ComicsData
import com.example.marvelapplication.model.events.MarvelEventsData

sealed class ApiResponse {
    data class CharacterResponseOk(
        var code: String? = null,
        var status: String? = null,
        var data: MarvelCharacterData,
    ) : ApiResponse()

    data class ComicResponseOk(
        var code: String? = null,
        var status: String? = null,
        var data: ComicsData,
    ) : ApiResponse()

    data class EventResponseOk(
        var code: String? = null,
        var status: String? = null,
        var data: MarvelEventsData,
    ) : ApiResponse()
}
