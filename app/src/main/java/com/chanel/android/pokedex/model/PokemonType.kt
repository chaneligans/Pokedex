package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a Pokemon's type
 * @property slot number representing the order this type is relative to the Pokemon
 * @property typeResult simple details of the type
 */
@Parcelize
@JsonClass(generateAdapter=true)
data class PokemonType(
    val slot: Int,
    @Json(name="type") val typeResult: TypeResult
) : Parcelable