package com.example.masterytechtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Driver (
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val details: DriverDetails
        ) : Parcelable