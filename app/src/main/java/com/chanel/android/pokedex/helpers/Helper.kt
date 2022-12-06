package com.chanel.android.pokedex.helpers

object Helper {

    fun getPokemonImageUrl(id: Int): String {
        val validId = getValidId(id)
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$validId.png"
    }

    fun getValidId(id: Int): String {
        val idString = id.toString()
        val zeroesToAdd = 3 - idString.length
        var zeroes = ""
        for (i in 0 until zeroesToAdd) {
            zeroes += "0"
        }
        return "$zeroes$idString"
    }
}