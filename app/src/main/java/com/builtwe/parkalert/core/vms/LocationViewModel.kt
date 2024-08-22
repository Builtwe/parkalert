package com.builtwe.parkalert.core.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.android.gms.location.LocationResult

class LocationViewModel : ViewModel() {
    private val _locationData = MutableStateFlow<LocationResult?>(null)
    val locationData: StateFlow<LocationResult?> = _locationData

    fun updateLocation(locationResult: LocationResult) {
        _locationData.value = locationResult
        Log.d("Parkalert", "im here")
    }
}
