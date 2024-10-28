package com.example.rescuedanimals.presentation.screens.favoriteScreen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rescuedanimals.presentation.navigation.Screen
import com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen.RescuedAnimalScreen

fun NavGraphBuilder.favoriteScreenRoute(navController: NavController){
    composable(Screen.FavoriteScreen.route){
        FavoriteScreen(navController = navController)
    }
}