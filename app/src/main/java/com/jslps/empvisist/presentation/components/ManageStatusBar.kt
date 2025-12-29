package com.jslps.empvisist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun manageStatusBar() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true // Change to false for white icons
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Blue, // Change color here
            darkIcons = useDarkIcons
        )
    }
}