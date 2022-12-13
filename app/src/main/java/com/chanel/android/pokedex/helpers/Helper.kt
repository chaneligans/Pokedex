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

    fun getTypeColorPastel(type: String): Int {
        return when (type) {
            "normal" -> R.color.normal_pastel
            "fire" -> R.color.fire_pastel
            "water" -> R.color.water_pastel
            "electric" -> R.color.electric_pastel
            "grass" -> R.color.grass_pastel
            "ice" -> R.color.ice_pastel
            "fighting" -> R.color.fighting_pastel
            "poison" -> R.color.poison_pastel
            "ground" -> R.color.ground_pastel
            "flying" -> R.color.flying_pastel
            "psychic" -> R.color.psychic_pastel
            "bug" -> R.color.bug_pastel
            "rock" -> R.color.rock_pastel
            "ghost" -> R.color.ghost_pastel
            "dragon" -> R.color.dragon_pastel
            "dark" -> R.color.dark_pastel
            "steel" -> R.color.steel_pastel
            "fairy" -> R.color.fairy_pastel
            else -> 0
        }
    }
}