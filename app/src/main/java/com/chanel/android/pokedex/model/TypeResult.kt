package com.chanel.android.pokedex.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Data class representing the type result from a [PokemonType]
 * @property name name of the type
 * @property url path to the details of the type
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class TypeResult(
    val name: String,
    val url: String
) : Parcelable