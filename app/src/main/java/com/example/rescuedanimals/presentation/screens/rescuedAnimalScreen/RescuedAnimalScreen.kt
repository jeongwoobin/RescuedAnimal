package com.example.rescuedanimals.presentation.screens.rescuedAnimalScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.example.rescuedanimals.presentation.component.BaseScreen
import com.example.rescuedanimals.presentation.component.CustomPullToRefreshBox
import com.example.rescuedanimals.presentation.component.GoToTopFAB
import com.example.rescuedanimals.presentation.component.Header
import com.example.rescuedanimals.presentation.component.LinearProgressBar
import com.example.rescuedanimals.presentation.component.AnimalList
import com.example.rescuedanimals.presentation.component.HorizontalDivider
import com.example.rescuedanimals.presentation.component.VerticalDivider
import com.example.rescuedanimals.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun RescuedAnimalScreen(
    navController: NavController,
    rescuedAnimalViewModel: RescuedAnimalViewModel = hiltViewModel()
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbar by rescuedAnimalViewModel.snackbarEvent.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        rescuedAnimalViewModel.getRescuedAnimal(refresh = true)
    }

    LaunchedEffect(snackbar) {
        snackbar.getContentIfNotHandled()?.let {
            snackbarHostState.showSnackbar(it, null)
        }
    }

    BaseScreen(
        snackbarHostState = snackbarHostState,
        loadingStateFlow = rescuedAnimalViewModel.resultState,
        loadingProgressBar = { LinearProgressBar() },
        fab = {
            GoToTopFAB(onClicked = {
                coroutineScope.launch {
                    // Scroll to the top of the list when the FAB is clicked
                    listState.animateScrollToItem(0)
                }
            })
        }) {
        if (windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT)
            Column {
                Header(
                    route = Screen.RescuedAnimalScreen,
                    rightButtonClicked = {
                        navController.navigate(Screen.FavoriteScreen.route)
                    })
                HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
                CustomPullToRefreshBox(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                    onRefresh = { rescuedAnimalViewModel.getRescuedAnimal(refresh = true) }) {
                    AnimalList(
                        modifier = Modifier.fillMaxSize(),
                        listState = listState,
                        itemListState = rescuedAnimalViewModel.rescuedAnimalList,
                        onLoadMore = { refresh ->
                            coroutineScope.launch {
                                rescuedAnimalViewModel.getRescuedAnimal(
                                    refresh = refresh
                                )
                            }
                        },
                        itemClicked = { index, animal ->
                            coroutineScope.launch {
                                rescuedAnimalViewModel.insertFavoriteAnimal(
                                    index = index,
                                    animal = animal
                                )
                            }
                        }
                    )
                }
            }
        else
            Row {
                Header(
                    route = Screen.RescuedAnimalScreen,
                    rightButtonClicked = {
                        navController.navigate(Screen.FavoriteScreen.route)
                    })
                VerticalDivider(modifier = Modifier.padding(vertical = 20.dp))
                CustomPullToRefreshBox(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                    onRefresh = { rescuedAnimalViewModel.getRescuedAnimal(refresh = true) }) {
                    AnimalList(
                        modifier = Modifier.fillMaxSize(),
                        listState = listState,
                        itemListState = rescuedAnimalViewModel.rescuedAnimalList,
                        onLoadMore = { refresh ->
                            coroutineScope.launch {
                                rescuedAnimalViewModel.getRescuedAnimal(
                                    refresh = refresh
                                )
                            }
                        },
                        itemClicked = { index, animal ->
                            coroutineScope.launch {
                                rescuedAnimalViewModel.insertFavoriteAnimal(
                                    index = index,
                                    animal = animal
                                )
                            }
                        }
                    )
                }
            }
    }
}