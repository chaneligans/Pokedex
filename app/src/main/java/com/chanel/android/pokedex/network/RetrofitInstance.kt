package com.chanel.android.pokedex.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val POKEMON_API_URL = "https://pokeapi.co/api/v2/"

object RetrofitInstance {

    val pokemonApi: PokemonAPI by lazy {
        Retrofit.Builder()
            .baseUrl(POKEMON_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }

}