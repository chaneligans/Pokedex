package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing a Pokemon type
 * @property id id of the type
 * @property name name of the type
 */
@JsonClass(generateAdapter = true)
data class Type(
    val id: Int,
    val name: String,
)