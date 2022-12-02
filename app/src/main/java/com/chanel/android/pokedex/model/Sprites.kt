package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sprites (
    val front_default: String
)