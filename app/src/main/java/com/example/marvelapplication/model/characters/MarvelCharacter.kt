package com.example.marvelapplication.model.characters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.Thumbnail
import com.google.gson.annotations.SerializedName

@Entity("characters")
data class MarvelCharacter(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,

    @SerializedName("description")
    val description: String?,

    val end: String? = null,

    val start: String? = null,

) : GenericAnswer()
