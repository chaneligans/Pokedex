package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonAbility (
    val ability: Ability,
    @Json(name="is_hidden") val isHidden: Boolean,
    val slot: Int
) : Parcelable