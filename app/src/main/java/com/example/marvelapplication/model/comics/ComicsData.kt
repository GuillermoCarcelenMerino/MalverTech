package com.example.marvelapplication.model.comics

import com.google.gson.annotations.SerializedName

data class ComicsData(
    @SerializedName("results")
    val results: List<MarvelComic>?,
)
