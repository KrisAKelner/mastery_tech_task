package com.example.masterytechtask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val latitude: String,
    val longitude: String
        ) : Parcelable