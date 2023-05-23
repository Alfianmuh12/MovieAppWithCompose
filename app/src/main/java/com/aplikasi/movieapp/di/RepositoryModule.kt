package com.aplikasi.movieapp.di

import com.aplikasi.movieapp.data.local.MovieDao
import com.aplikasi.movieapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(movieDao: MovieDao) = Repository(movieDao)
}