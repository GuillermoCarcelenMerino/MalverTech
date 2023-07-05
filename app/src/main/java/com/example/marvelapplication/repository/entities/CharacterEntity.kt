package com.example.marvelapplication.repository.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.Thumbnail

@Entity("characters")
data class CharacterEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val name: String?,

    val thumbnail: Thumbnail?,

    val description: String?,

    val end: String? = null,

    val start: String? = null,

) : GenericAnswer()
