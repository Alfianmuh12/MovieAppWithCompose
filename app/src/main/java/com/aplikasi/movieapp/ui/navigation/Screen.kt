package com.aplikasi.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}