package com.example.marvelapplication.utils

import androidx.room.TypeConverter
import com.example.marvelapplication.model.Thumbnail

class ThumbnailtConverter {
    @TypeConverter
    fun fromStringList(thumbnail: Thumbnail): String {
        return thumbnail.path + ".jpg"
    }

    @TypeConverter
    fun toThumbnail(data: String): Thumbnail {
        val path = data.substringBeforeLast(".")

        return Thumbnail(path, "jpg")
    }
}
