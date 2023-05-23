package com.aplikasi.movieapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val desc: String,
    val photoUrl: String,
    val rating: Double,
    val year: String,
    val totalReview: String,
    var isFavorite:Boolean = false,
)
