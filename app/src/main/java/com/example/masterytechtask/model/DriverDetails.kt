package com.example.masterytechtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriverDetails (
    val trailerType: String,
    val trailerLength: Int,
    val trailerHeight: Int,
    val trailerWidth: Int,
    val plateNumber: String,
    val currentLocation: Location
        ) : Parcelable