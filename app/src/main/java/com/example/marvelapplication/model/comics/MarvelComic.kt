package com.example.marvelapplication.model.comics

import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.Thumbnail

data class MarvelComic(

    val id: Int?,

    val title: String?,

    val thumbnail: Thumbnail?,

    val description: String?,

    val end: String? = null,

    val start: String? = null,
) : GenericAnswer()
