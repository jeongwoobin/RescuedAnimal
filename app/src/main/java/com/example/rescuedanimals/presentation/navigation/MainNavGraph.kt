package com.example.rescuedanimals.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.rescuedanimals.data.util.LiveNetworkMonitor
import com.example.rescuedanimals.presentation.screens.favoriteScreen.favoriteScreenRoute
import com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen.rescuedAnimalScreenRoute

@Composable
fun MainNavGraph(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {

        // RescuedAnimalScreen
        rescuedAnimalScreenRoute(navController = navController)

        // favoriteScreen
        favoriteScreenRoute(navController = navController)
    }
}