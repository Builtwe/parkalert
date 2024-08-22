package com.builtwe.parkalert.location

import android.util.Log
import com.builtwe.parkalert.utils.SecretData.TAG
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LocationDataHolder {
    private val _locationData = MutableStateFlow<LocationResult?>(null)
    val locationData: StateFlow<LocationResult?> = _locationData

    fun updateLocation(locationResult: LocationResult) {
        _locationData.value = locationResult
        Log.d(
            TAG,
            "Location received by data holder: ${locationResult.lastLocation?.latitude} ${locationResult.lastLocation?.longitude}"
        )
    }
}