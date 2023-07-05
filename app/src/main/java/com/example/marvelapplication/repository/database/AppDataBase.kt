package com.example.marvelapplication.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapplication.repository.entities.CharacterEntity
import com.example.marvelapplication.repository.entities.ComicEntity
import com.example.marvelapplication.repository.entities.EventEntity
import com.example.marvelapplication.utils.ThumbnailtConverter

@Database(entities = [CharacterEntity::class, ComicEntity::class, EventEntity::class], version = 2)
@TypeConverters(ThumbnailtConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharactersDao
    abstract fun comicsDao(): ComicsDao
    abstract fun eventsDao(): EventsDao
}
