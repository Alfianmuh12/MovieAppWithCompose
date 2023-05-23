package com.aplikasi.movieapp.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aplikasi.movieapp.UiState
import com.aplikasi.movieapp.data.local.MovieEntity
import com.aplikasi.movieapp.ui.components.AvailableContent
import com.aplikasi.movieapp.ui.components.EmptyContent
import com.aplikasi.movieapp.ui.components.Loading

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoriteMovie.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> Loading()
            is UiState.Error -> EmptyContent()
            is UiState.Success -> {
                FavoriteContent(
                    listFavoriteMovie = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteFavorite = favoriteViewModel::updateFavoriteMovie
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteMovie: List<MovieEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteFavorite: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteMovie.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(listFavoriteMovie, navController, scaffoldState, onUpdateFavoriteFavorite)
    }
}