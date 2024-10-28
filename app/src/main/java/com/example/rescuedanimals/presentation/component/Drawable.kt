package com.example.rescuedanimals.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
fun VectorIcon(modifier: Modifier = Modifier, vector: ImageVector, tint: Color, contentDescription: String?) {
    Icon(
        modifier = modifier,
        painter = rememberVectorPainter(image = vector),
        tint = tint,
        contentDescription = contentDescription
    )
}