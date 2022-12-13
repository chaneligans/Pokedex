package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing the different sprites of a [Pokemon]
 * @property front_default url to the front default sprite
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Sprites (
    @Json(name = "front_default") val front_default_url: String,
    @Json(name = "front_shiny") val front_shiny_url: String,
    @Json(name = "back_default") val back_default_url: String,
    @Json(name = "back_shiny") val back_shiny_url: String,
): Parcelable