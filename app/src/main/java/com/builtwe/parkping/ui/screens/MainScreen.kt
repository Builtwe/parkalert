package com.builtwe.parkping.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.builtwe.parkping.R
import com.builtwe.parkping.ui.navigation.BottomNavItem
import com.builtwe.parkping.ui.navigation.ParkpingNavBar
import com.builtwe.parkping.ui.navigation.ParkpingNavHost
import com.builtwe.parkping.ui.navigation.Screen

@Composable
fun MainScreen() {
    val topLevelDestinations = listOf(
        BottomNavItem(
            route = Screen.MapScreen.route,
            labelRes = R.string.map,
            selectedIcon = Icons.Default.LocationOn,
            unselectedIcon = Icons.Default.LocationOn
        ),
        BottomNavItem(
            route = Screen.ProfileScreen.route,
            labelRes = R.string.profile,
            selectedIcon = Icons.Default.AccountCircle,
            unselectedIcon = Icons.Default.AccountCircle
        ),
        BottomNavItem(
            route = Screen.SettingsScreen.route,
            labelRes = R.string.settings,
            selectedIcon = Icons.Default.Settings,
            unselectedIcon = Icons.Default.Settings
        )
    )

    val localNavController = rememberNavController()
    val currentRoute = localNavController
        .currentBackStackEntryAsState()
        .value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in topLevelDestinations.map { it.route }) {
                ParkpingNavBar(
                    navController = localNavController,
                    destinations = topLevelDestinations
                )
            }
        },
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ParkpingNavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                navController = localNavController
            )
        }
    }
}