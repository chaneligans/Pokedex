package com.chanel.android.pokedex

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chanel.android.pokedex.databinding.PokemonItemBinding
import com.chanel.android.pokedex.helpers.Helper.getPokemonImageUrl
import com.chanel.android.pokedex.helpers.Helper.getValidId
import com.chanel.android.pokedex.model.Pokemon

class PokemonItemHolder(
    private val binding: PokemonItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pokemon: Pokemon,
        onPokemonClicked: (Pokemon) -> Unit
    ) {
        binding.apply {
            pokemonInfoLayout.removeAllViews()
            val validId = getValidId(pokemon.id)
            val imageUrl = getPokemonImageUrl(pokemon.id)
            pokemonImage.load(imageUrl)
            pokemonIdText.text = "#$validId"
            pokemonNameText.text = pokemon.name
            pokemon.types.forEach { type ->
                val textView = TextView(pokemonNameText.context)
                textView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textView.text = type.typeResult.name
                pokemonInfoLayout.addView(textView)
            }

            binding.root.setOnClickListener {
                onPokemonClicked(pokemon)
            }
        }
    }

}