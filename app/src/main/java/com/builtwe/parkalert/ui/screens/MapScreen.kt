package com.builtwe.parkalert.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.builtwe.parkalert.core.vms.LocationViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

// https://github.com/yandex/mapkit-android-demo/issues/317#issuecomment-2182595712
@Composable
fun MapScreen() {
    val context = LocalContext.current
    val mapView = remember { mutableStateOf<MapView?>(null) }
    val locationViewModel = LocationViewModel()
    val locationResult by locationViewModel.locationData.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(top = 0.dp)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AndroidView(
                factory = { MapView(context) },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                mapView.value = it
            }
            Text(
                text = "Location: ${locationResult?.lastLocation}",
                modifier = Modifier.padding(32.dp)
            )
        }
    }

    LaunchedEffect(key1 = "loadMapView") {
        snapshotFlow { mapView.value }.collect {
            it?.let {
                MapKitFactory.initialize(context)
                MapKitFactory.getInstance().onStart()
                it.onStart()
            }
        }
    }
}