package com.chanel.android.pokedex.helpers

import com.chanel.android.pokedex.R

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

    fun getTypeColor(type: String): Int {
        return when (type) {
            "normal" -> R.color.normal
            "fire" -> R.color.fire
            "water" -> R.color.water
            "electric" -> R.color.electric
            "grass" -> R.color.grass
            "ice" -> R.color.ice
            "fighting" -> R.color.fighting
            "poison" -> R.color.poison
            "ground" -> R.color.ground
            "flying" -> R.color.flying
            "psychic" -> R.color.psychic
            "bug" -> R.color.bug
            "rock" -> R.color.rock
            "ghost" -> R.color.ghost
            "dragon" -> R.color.dragon
            "dark" -> R.color.dark
            "steel" -> R.color.steel
            "fairy" -> R.color.fairy
            else -> 0
        }
    }
}