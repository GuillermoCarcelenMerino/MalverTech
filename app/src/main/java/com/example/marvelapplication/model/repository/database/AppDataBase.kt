package com.example.marvelapplication.model.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapplication.model.events.MarvelEvent
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.utils.ThumbnailtConverter

@Database(entities = [MarvelCharacter::class, MarvelComic::class, MarvelEvent::class], version = 2)
@TypeConverters(ThumbnailtConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
    abstract fun comicsDao(): ComicsDao
    abstract fun eventsDao(): EventsDao
}
