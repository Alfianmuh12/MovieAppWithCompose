package com.aplikasi.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getAllFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movie WHERE name LIKE '%' || :query || '%'")
    fun searchMovie(query: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(movieList: List<MovieEntity>)

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteMovie(id: Int, isFavorite: Boolean)
}