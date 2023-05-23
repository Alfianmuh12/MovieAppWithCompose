package com.aplikasi.movieapp.data.repository

import com.aplikasi.movieapp.data.local.MovieDao
import com.aplikasi.movieapp.data.local.MovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovie() = movieDao.getAllMovie()
    fun getAllFavoriteMovie() = movieDao.getAllFavoriteMovie()
    fun getMovie(id: Int) = movieDao.getMovie(id)
    fun searchMovie(query: String) = movieDao.searchMovie(query)
    suspend fun insertAllMovie(movie: List<MovieEntity>) = movieDao.insertAllMovie(movie)
    suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean) = movieDao.updateFavoriteMovie(id, isFavorite)
}