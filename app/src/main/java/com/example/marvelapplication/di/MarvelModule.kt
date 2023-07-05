package com.example.marvelapplication.di

import android.content.Context
import androidx.room.Room
import com.example.marvelapplication.repository.database.AppDatabase
import com.example.marvelapplication.repository.database.CharactersDao
import com.example.marvelapplication.repository.database.ComicsDao
import com.example.marvelapplication.repository.database.EventsDao
import com.example.marvelapplication.repository.network.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelModule {

    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit.Builder().baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit) = retrofit.create(MarvelApi::class.java)

    @Singleton
    @Provides
    fun providesBD(@ApplicationContext appContext: Context): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "Marvel",
    ).build()

    @Singleton
    @Provides
    fun providesDao(appBD: AppDatabase): CharactersDao = appBD.characterDao()

    @Singleton
    @Provides
    fun providesDaoComic(appBD: AppDatabase): ComicsDao = appBD.comicsDao()

    @Singleton
    @Provides
    fun providesDaoEvents(appBD: AppDatabase): EventsDao = appBD.eventsDao()
}
