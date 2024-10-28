package com.example.rescuedanimals.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rescuedanimals.ui.theme.Line_Thin

@Composable
fun ScreenDivider(modifier: Modifier = Modifier, color: Color = Line_Thin) {
    HorizontalDivider(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth(), color = color
    )
}