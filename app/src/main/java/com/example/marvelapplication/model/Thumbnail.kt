package com.example.marvelapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    @SerializedName("path")
    var path: String? = null,
    @SerializedName("extension")
    var extension: String? = null,
) : Parcelable
