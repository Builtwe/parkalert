package com.builtwe.parkalert.ui.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object MapScreen : Screen("map_screen")
    data object ProfileScreen : Screen("profile_screen")
    data object SettingsScreen : Screen("settings_screen")
}