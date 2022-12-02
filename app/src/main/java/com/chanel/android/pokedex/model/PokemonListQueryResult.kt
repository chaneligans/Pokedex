package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

/**
 * Data class representing the results from querying for a list of Pokemon.
 * @property count the total number of possible results
 * @property next url to retrieve the next 20 results - null if no more results
 * @property previous url to retrieve the previous 20 results - null if none
 * @property results list of [PokemonQueryResult]s returned from the query
 */
@JsonClass(generateAdapter = true)
data class PokemonListQueryResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonQueryResult>
)