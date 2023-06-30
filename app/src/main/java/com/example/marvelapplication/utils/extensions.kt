package com.example.marvelapplication.utils

import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.marvelapplication.R
import com.example.marvelapplication.model.Thumbnail
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.model.events.MarvelEvent
import java.util.*

fun Thumbnail.getThumbnail(): String {
    return "${this.path}.${this.extension}"
}

fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
        .onLoadFailed(R.drawable.ic_person.toDrawable())
}

fun Date.dateToInt(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return year * 10000 + month * 100 + day
}

fun getCurrentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun List<MarvelComic>.toMarvelCharacter() = this.map {
    MarvelCharacter(
        it.id,
        it.name,
        it.thumbnail,
        it.description,
    )
}

fun List<MarvelEvent>.toMarvelCharacterEvent() = this.map {
    MarvelCharacter(
        it.id,
        it.name,
        it.thumbnail,
        it.description,
        it.end,
        it.start,
    )
}
