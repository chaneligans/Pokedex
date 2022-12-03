package com.chanel.android.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chanel.android.pokedex.databinding.FragmentSinglePokemonInputBinding

class SinglePokemonInputFragment: Fragment() {

    private var _binding: FragmentSinglePokemonInputBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access FragmentSinglePokemonInputBinding because it is null. Is the view visible?"
        }

    private val viewModel: PokedexViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinglePokemonInputBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            Log.d("chanelz", "$pokemon")
            findNavController().navigate(
                SinglePokemonInputFragmentDirections.showPokemonDetails(pokemon)
            )
        }

        binding.generateButton.setOnClickListener {
            onButtonClicked()
        }
    }

    fun onButtonClicked() {
        val pokemonInput = binding.pokemonInput.text.toString()
        viewModel.getSinglePokemon(pokemonInput)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}