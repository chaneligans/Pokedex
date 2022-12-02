package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListQueryResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonQueryResult>
)