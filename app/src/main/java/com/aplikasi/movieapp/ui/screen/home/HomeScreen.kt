package com.aplikasi.movieapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aplikasi.movieapp.UiState
import com.aplikasi.movieapp.data.local.MovieEntity
import com.aplikasi.movieapp.ui.components.AvailableContent
import com.aplikasi.movieapp.ui.components.EmptyContent
import com.aplikasi.movieapp.ui.components.Loading
import com.aplikasi.movieapp.ui.components.SearchBar

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allMovie.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> Loading()
            is UiState.Error -> EmptyContent()
            is UiState.Success -> {
                HomeContent(
                    listMovie = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoriteTourism = homeViewModel::updateFavoriteTourism
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listMovie: List<MovieEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteTourism: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listMovie.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(listMovie, navController, scaffoldState, onUpdateFavoriteTourism)
        }
    }
}