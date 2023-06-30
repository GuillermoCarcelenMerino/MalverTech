package com.example.marvelapplication.model.events

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.Thumbnail
import com.google.gson.annotations.SerializedName

@Entity("events")
data class MarvelEvent(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val name: String?,

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,

    @SerializedName("description")
    val description: String?,

    val end: String? = null,

    val start: String? = null,

) : GenericAnswer()
