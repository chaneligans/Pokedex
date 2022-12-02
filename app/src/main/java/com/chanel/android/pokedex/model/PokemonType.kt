package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class PokemonType(
    val slot: Int,
    val type: TypeResult
)