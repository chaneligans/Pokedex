package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing the type result from a [PokemonType]
 * @property name name of the type
 * @property url path to the details of the type
 */
@JsonClass(generateAdapter = true)
data class TypeResult(
    val name: String,
    val url: String
)