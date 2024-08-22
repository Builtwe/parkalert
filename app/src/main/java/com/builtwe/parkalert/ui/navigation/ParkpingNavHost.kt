package com.builtwe.parkalert.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.builtwe.parkalert.ui.screens.MainScreen
import com.builtwe.parkalert.ui.screens.MapScreen
import com.builtwe.parkalert.ui.screens.ProfileScreen
import com.builtwe.parkalert.ui.screens.SettingsScreen
import com.builtwe.parkalert.ui.screens.SignUpScreen

@Composable
fun ParkpingNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MapScreen.route,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
        composable(Screen.MapScreen.route) {
            MapScreen()
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
    }
}