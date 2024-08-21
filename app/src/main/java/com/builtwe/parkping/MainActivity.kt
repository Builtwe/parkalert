package com.builtwe.parkping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.builtwe.parkping.ui.screens.MainScreen
import com.builtwe.parkping.ui.theme.ParkpingTheme
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("f780eac5-f6a0-43bd-a286-e2cf79536159")

        enableEdgeToEdge()
        setContent {
            ParkpingTheme {
                MainScreen()
            }
        }
    }
}