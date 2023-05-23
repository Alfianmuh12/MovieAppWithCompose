package com.aplikasi.movieapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplikasi.movieapp.UiState
import com.aplikasi.movieapp.data.local.MovieEntity
import com.aplikasi.movieapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _movie = MutableStateFlow<UiState<MovieEntity>>(UiState.Loading)
    val movie = _movie.asStateFlow()

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovie(id)
                .catch { _movie.value = UiState.Error(it.message.toString()) }
                .collect { _movie.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteMovie(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteMovie(id, isFavorite)
        }
    }
}