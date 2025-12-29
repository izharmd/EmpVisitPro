package com.jslps.empvisist.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

enum class GradientType { BLUE, GREEN, ORANGE }

@Composable
fun AppGradientBackground(
    type: GradientType = GradientType.BLUE,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = when (type) {
        GradientType.BLUE -> listOf(Color(0xFFF8FAF8), Color(0xFFFAF8F7), Color(0xFFF5F5F5))
        GradientType.GREEN -> listOf(Color(0xFF009E4E), Color(0xFF52C378), Color(0xFFA8E9B5))
        GradientType.ORANGE -> listOf(Color(0xFF7E7771), Color(0xFFFF8A34), Color(0xFFFFB375))
    }

    val gradient = Brush.verticalGradient(colors)

    Box(modifier = modifier.background(gradient)) {
        content()
    }
}