package com.jslps.empvisist.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val gradientButton: Brush = Brush.horizontalGradient(
    colors = listOf(Color(0xFF274DA9), Color(0xFF88C2BC))
)
val borderGradient = Brush.horizontalGradient(listOf(Color(0xFF833AB4), Color(0xFF4B705A)))
val borderButton = BorderStroke(1.dp, borderGradient)

val buttonRoundedShape = RoundedCornerShape(10.dp)

val gradientUserDetails = Brush.horizontalGradient(
    colors = listOf(Color(0xFFF9FAF9), Color(0xFFFFFFFF), Color(0xFFF2F5F3)

    )
)

val borderGradientCardView = Brush.horizontalGradient(listOf(Color(0xFF833AB4), Color(0xFFF77737)))
val gradientCardView = BorderStroke(3.dp, borderGradientCardView)
    val gradientBackGroundCardView = Brush.horizontalGradient(
        colors = listOf(Color(0xBE14668D), Color(0xD35CA45F))
    )


val gradientToolBar = Brush.horizontalGradient(
    colors = listOf(Color(0xFF0A53CB), Color(0xFF0A53CB), Color(0xFF0A53CB))
)

val roundShapeCardView = RoundedCornerShape(10.dp)