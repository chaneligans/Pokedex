package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a stat
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Stat(
    val name: String
) : Parcelable
