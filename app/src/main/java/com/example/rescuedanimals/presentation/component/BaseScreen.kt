package com.example.rescuedanimals.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rescuedanimals.domain.entity.Result
import com.example.rescuedanimals.domain.entity.Status
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BaseScreen(
    snackbarHostState: SnackbarHostState,
    loadingStateFlow: StateFlow<Result<Any>>,
    loadingProgressBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val loading by loadingStateFlow.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        floatingActionButton = { fab() }) {
        Box {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {
                content()
                if (loading.status == Status.LOADING) {
                    loadingProgressBar()
                }
            }

            DefaultSnackBar(
                snackBarHostState = snackbarHostState,
                modifier = Modifier.align(alignment = Alignment.BottomCenter)
            )
        }
    }
}