package com.chanel.android.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.chanel.android.pokedex.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsFragment: Fragment() {

    private val args: PokemonDetailsFragmentArgs by navArgs()

    private var _binding: FragmentPokemonDetailsBinding? = null
    val binding
        get() = checkNotNull(_binding) {
        "Cannot access FragmentPokemonDetailsBinding because it is null. Is the view visible?"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemonData = args.pokemon
        Log.d("chanelz", "pokemon: ${pokemonData}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.apply {
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}