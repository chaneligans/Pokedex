package com.chanel.android.pokedex.network

import com.chanel.android.pokedex.model.Pokemon
import com.chanel.android.pokedex.model.PokemonListQueryResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: String): Response<Pokemon>

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<PokemonListQueryResult>

}