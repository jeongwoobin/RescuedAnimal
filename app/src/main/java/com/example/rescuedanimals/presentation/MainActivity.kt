package com.example.rescuedanimals.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.rescuedanimals.BuildConfig
import com.example.rescuedanimals.presentation.component.ScreenDivider
import com.example.rescuedanimals.presentation.navigation.MainNavGraph
import com.example.rescuedanimals.ui.theme.Primary_Red_500
import com.example.rescuedanimals.ui.theme.Purple200
import com.example.rescuedanimals.ui.theme.RescuedAnimalsTheme
import com.example.rescuedanimals.ui.theme.Success
import com.orhanobut.logger.Logger
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