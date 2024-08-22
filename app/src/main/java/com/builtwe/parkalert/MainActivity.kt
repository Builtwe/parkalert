package com.builtwe.parkalert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.builtwe.parkalert.ui.screens.MainScreen
import com.builtwe.parkalert.ui.theme.ParkpingTheme
import com.yandex.mapkit.MapKitFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("f780eac5-f6a0-43bd-a286-e2cf79536159") // nuh uh

        enableEdgeToEdge()
        setContent {
            ParkpingTheme {
                MainScreen()
            }
        }
    }
}