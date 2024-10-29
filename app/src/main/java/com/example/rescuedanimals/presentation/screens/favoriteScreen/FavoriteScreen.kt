package com.example.rescuedanimals.presentation.screens.favoriteScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
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
import com.example.rescuedanimals.presentation.component.BaseScreen
import com.example.rescuedanimals.presentation.component.CustomPullToRefreshBox
import com.example.rescuedanimals.presentation.component.GoToTopFAB
import com.example.rescuedanimals.presentation.component.Header
import com.example.rescuedanimals.presentation.component.HorizontalDivider
import com.example.rescuedanimals.presentation.navigation.Screen
import com.example.rescuedanimals.presentation.component.AnimalList
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbar by favoriteViewModel.snackbarEvent.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        favoriteViewModel.getFavoriteAnimal()
    }

    LaunchedEffect(snackbar) {
        snackbar.getContentIfNotHandled()?.let {
            snackbarHostState.showSnackbar(it, null)
        }
    }

    BaseScreen(snackbarHostState = snackbarHostState,
        loadingStateFlow = favoriteViewModel.resultState,
        fab = {
            GoToTopFAB(onClicked = {
                coroutineScope.launch {
                    // Scroll to the top of the list when the FAB is clicked
                    listState.animateScrollToItem(0)
                }
            })
        }) {
        Header(
            route = Screen.FavoriteScreen,
            rightButtonClicked = {
                navController.navigate(Screen.RescuedAnimalScreen.route)
            })
        HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp))
        CustomPullToRefreshBox(
            modifier = Modifier
//                .weight(1f)
                .padding(horizontal = 20.dp),
            onRefresh = { favoriteViewModel.getFavoriteAnimal() }) {
            AnimalList(
                modifier = Modifier.fillMaxSize(),
                listState = listState,
                itemListState = favoriteViewModel.favoriteAnimalList,
                onLoadMore = { refresh ->
                    coroutineScope.launch {
                        favoriteViewModel.getFavoriteAnimal()
                    }
                },
                itemClicked = { index, animal -> }
            )
        }
    }
}