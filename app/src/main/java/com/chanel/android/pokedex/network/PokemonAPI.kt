package com.chanel.android.pokedex.network

import com.chanel.android.pokedex.model.Pokemon
import com.chanel.android.pokedex.model.PokemonListQueryResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Single

interface PokemonAPI {

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: String): Observable<Pokemon>

    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Observable<PokemonListQueryResult>

}