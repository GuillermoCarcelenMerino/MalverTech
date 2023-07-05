package com.example.marvelapplication.model.characters

import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.Thumbnail

data class MarvelCharacter(

    val id: Int?,

    val name: String?,

    val thumbnail: Thumbnail?,

    val description: String?,

    val end: String? = null,

    val start: String? = null,

) : GenericAnswer()
