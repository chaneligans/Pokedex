package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing the different sprites of a [Pokemon]
 * @property front_default url to the front default sprite
 */
@JsonClass(generateAdapter = true)
data class Sprites (
    val front_default: String
)