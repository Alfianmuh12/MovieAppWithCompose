package com.aplikasi.movieapp.di

import android.app.Application
import androidx.room.Room
import com.aplikasi.movieapp.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, MovieDatabase::class.java, "movieapp.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: MovieDatabase) = database.movieDao()
}