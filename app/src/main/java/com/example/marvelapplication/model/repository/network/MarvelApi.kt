package com.example.marvelapplication.model.repository.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    /** We add limit to get a specific number of characters and an offset in order of paging the results**/

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<ApiResponse.CharacterResponseOk>

    @GET("comics")
    suspend fun getComics(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<ApiResponse.ComicResponseOk>

    @GET("events")
    suspend fun getEvents(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<ApiResponse.EventResponseOk>
}
