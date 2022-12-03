package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a single Pokemon.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<PokemonType>,
    val sprites: Sprites,
) : Parcelable