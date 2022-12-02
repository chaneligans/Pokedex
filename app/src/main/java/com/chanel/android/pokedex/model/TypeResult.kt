package com.chanel.android.pokedex.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeResult(
    val name: String,
    val url: String
)