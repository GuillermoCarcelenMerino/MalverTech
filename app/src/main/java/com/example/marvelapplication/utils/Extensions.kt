package com.example.marvelapplication.utils

import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.model.events.MarvelEvent
import com.example.marvelapplication.repository.entities.CharacterEntity
import com.example.marvelapplication.repository.entities.ComicEntity
import com.example.marvelapplication.repository.entities.EventEntity
import java.util.*

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
        it.title,
        it.thumbnail,
        it.description,
    )
}

fun List<MarvelEvent>.toMarvelCharacterEvent() = this.map {
    MarvelCharacter(
        it.id,
        it.title,
        it.thumbnail,
        it.description,
        it.end,
        it.start,
    )
}

fun List<ComicEntity>.fromEntityToCharacter() = this.map {
    MarvelCharacter(
        it.id,
        it.title,
        it.thumbnail,
        it.description,
    )
}

fun List<EventEntity>.fromEntityToCharacterEvent() = this.map {
    MarvelCharacter(
        it.id,
        it.title,
        it.thumbnail,
        it.description,
        it.end,
        it.start,
    )
}

fun List<CharacterEntity>.fromEntity() = this.map {
    MarvelCharacter(
        it.id,
        it.name,
        it.thumbnail,
        it.description,
        it.end,
        it.start,
    )
}

fun MarvelCharacter.toEntity() = CharacterEntity(
    null,
    this.name,
    this.thumbnail,
    this.description,
)

fun MarvelComic.toEntity() = ComicEntity(
    null,
    this.title,
    this.thumbnail,
    this.description,
)

fun MarvelEvent.toEntity() = EventEntity(
    null,
    this.title,
    this.thumbnail,
    this.description,
    this.end,
    this.start,
)
