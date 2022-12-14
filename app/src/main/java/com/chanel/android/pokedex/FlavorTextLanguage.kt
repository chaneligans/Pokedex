package com.chanel.android.pokedex

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FlavorTextLanguage (
    val name: String
) : Parcelable