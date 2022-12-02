package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val sprites: Sprites,
)