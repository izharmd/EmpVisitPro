package com.jslps.empvisist.presentation.navigation

sealed class Screens(val route: String) {
    data object SplashScreen : Screens("splash")
    data object LoginScreen : Screens("loginScreen")
    data object DashboardScreen : Screens("dashboard")
    data object SectionScreen : Screens("sectionScreen")
    data object HomeScreen : Screens("homeScreen")
    data object SettingsScreen : Screens("settingsScreen")
}