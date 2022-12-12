package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing a Pokemon stat.
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonStat(
    @Json(name="base_stat") val baseStat: Int,
    val stat: Stat
) : Parcelable
