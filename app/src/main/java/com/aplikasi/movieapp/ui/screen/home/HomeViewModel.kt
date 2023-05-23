package com.aplikasi.movieapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplikasi.movieapp.UiState
import com.aplikasi.movieapp.data.local.MovieData
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
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allMovie = MutableStateFlow<UiState<List<MovieEntity>>>(UiState.Loading)
    val allMovie = _allMovie.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovie().collect { movie ->
                when (movie.isEmpty()) {
                    true -> repository.insertAllMovie(MovieData.dummy)
                    else -> _allMovie.value = UiState.Success(movie)
                }
            }
        }
    }

    private fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchMovie(query)
                .catch { _allMovie.value = UiState.Error(it.message.toString()) }
                .collect { _allMovie.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteTourism(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteMovie(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        searchMovie(query)
    }
}