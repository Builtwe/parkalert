package com.builtwe.parkalert.ui.screens

import android.graphics.PointF
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.builtwe.parkalert.R
import com.builtwe.parkalert.location.LocationDataHolder.locationData
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

// https://github.com/yandex/mapkit-android-demo/issues/317#issuecomment-2182595712
@Composable
fun MapScreen() {
    val context = LocalContext.current
    val mapView = remember { mutableStateOf<MapView?>(null) }
    val locationResult by locationData.collectAsState()

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
                text = "Location: ${locationResult?.lastLocation?.latitude} ${locationResult?.lastLocation?.longitude}",
                modifier = Modifier.padding(32.dp)
            )
        }
    }

    LaunchedEffect(key1 = "loadMapView") {
        snapshotFlow { mapView.value }.collect { mapView ->
            mapView?.let {
                MapKitFactory.initialize(context)
                MapKitFactory.getInstance().onStart()
                mapView.onStart()

                mapView.mapWindow.map.move(
                    com.yandex.mapkit.map.CameraPosition(Point(59.935493, 30.327392), 14.0f, 0.0f, 0.0f)
                )

                val placeMark = mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                    geometry = Point(59.935493, 30.327392)
                    setText(
                        "Special place",
                        TextStyle().apply {
                            size = 10f
                            placement = TextStyle.Placement.RIGHT
                            offset = 5f
                        },
                    )
                }

                placeMark.useCompositeIcon().apply {
                    setIcon(
                        "pin",
                        ImageProvider.fromResource(context, R.drawable.flag),
                        IconStyle().apply {
                            anchor = PointF(0.5f, 1.0f)
                            scale = 0.09f
                        }
                    )
                }
            }
        }
    }
}