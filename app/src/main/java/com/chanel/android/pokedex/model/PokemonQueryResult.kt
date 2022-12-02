package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing a query result from a [PokemonListQueryResult]
 * @property name name of the Pokemon
 * @property url path to the details of the Pokemon
 */
@JsonClass(generateAdapter = true)
data class PokemonQueryResult(
    val name: String,
    val url: String
)