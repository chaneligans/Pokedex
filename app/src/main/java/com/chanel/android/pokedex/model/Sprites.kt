package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing the different sprites of a [Pokemon]
 * @property front_default url to the front default sprite
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Sprites (
    val front_default: String
): Parcelable