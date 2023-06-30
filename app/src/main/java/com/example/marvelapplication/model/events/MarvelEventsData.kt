package com.example.marvelapplication.model.events

import com.google.gson.annotations.SerializedName

data class MarvelEventsData(
    @SerializedName("results")
    val results: List<MarvelEvent>?,
)
