package com.example.marvelapplication.model.repository.network

import com.example.marvelapplication.model.characters.MarvelCharacterData
import com.example.marvelapplication.model.comics.ComicsData
import com.example.marvelapplication.model.events.MarvelEventsData
import com.google.gson.annotations.SerializedName

sealed class ApiResponse {
    data class CharacterResponseOk(
        @SerializedName("code")
        var code: String? = null,

        @SerializedName("status")
        var status: String? = null,
        @SerializedName("data")
        var data: MarvelCharacterData,
    ) : ApiResponse()

    data class ComicResponseOk(
        @SerializedName("code")
        var code: String? = null,

        @SerializedName("status")
        var status: String? = null,

        @SerializedName("data")
        var data: ComicsData,
    ) : ApiResponse()

    data class EventResponseOk(
        @SerializedName("code")
        var code: String? = null,

        @SerializedName("status")
        var status: String? = null,

        @SerializedName("data")
        var data: MarvelEventsData,
    ) : ApiResponse()
}
