package com.chanel.android.pokedex.network

import com.chanel.android.pokedex.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: String): Response<Pokemon>

}