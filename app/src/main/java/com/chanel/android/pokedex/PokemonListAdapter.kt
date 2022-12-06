package com.chanel.android.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.chanel.android.pokedex.databinding.PokemonItemBinding
import com.chanel.android.pokedex.model.Pokemon

class PokemonListAdapter(
    private val pokemons: List<Pokemon>,
    private val onPokemonClicked: (pokemon: Pokemon) -> Unit,
): ListAdapter<Pokemon, PokemonItemHolder>(PokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemBinding.inflate(inflater, parent, false)
        return PokemonItemHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonItemHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon, onPokemonClicked)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}