package com.example.rescuedanimals.presentation.navigation

sealed class Screen(val route: String) {
    data  object RescuedAnimalScreen : Screen( "RescuedAnimalScreen" )
    data  object FavoriteScreen : Screen( "FavoriteScreen" )
}