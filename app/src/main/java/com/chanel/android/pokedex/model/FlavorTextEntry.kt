package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FlavorTextEntry(
    @Json(name="flavor_text") val flavorText: String,
    val language: FlavorTextLanguage
) : Parcelable
