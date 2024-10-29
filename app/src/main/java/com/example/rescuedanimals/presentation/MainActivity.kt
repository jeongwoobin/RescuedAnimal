package com.example.rescuedanimals.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.rescuedanimals.presentation.navigation.MainNavGraph
import com.example.rescuedanimals.ui.theme.RescuedAnimalsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            RescuedAnimalsTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize().background(color = Color.White)) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        SetNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun SetNavigation() {
    val navController = rememberNavController()
    MainNavGraph(navController)
}