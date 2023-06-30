package com.example.marvelapplication.model.characters

import com.google.gson.annotations.SerializedName

data class MarvelCharacterData(
    @SerializedName("results")
    val results: List<MarvelCharacter>?
)