package com.chanel.android.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chanel.android.pokedex.databinding.FragmentSinglePokemonInputBinding
import com.chanel.android.pokedex.model.Pokemon

class SinglePokemonInputFragment: Fragment() {

    private var _binding: FragmentSinglePokemonInputBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access FragmentSinglePokemonInputBinding because it is null. Is the view visible?"
        }

    private val viewModel: SinglePokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinglePokemonInputBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner) { event ->
            onPokemonChangedEvent(event)
        }

        binding.generateButton.setOnClickListener {
            onButtonClicked()
        }
    }

    private fun onButtonClicked() {
        val pokemonInput = binding.pokemonInput.text.toString()
        if (pokemonInput == "") {
            showErrorMessage()
        } else {
            viewModel.getSinglePokemon(pokemonInput)
        }
    }

    private fun onPokemonChangedEvent(event: Event<Pokemon>) {
        val pokemon = event.getContentIfNotHandled()
        pokemon?.let {
            Log.d("chanelz", "$it")
            findNavController().navigate(
                SinglePokemonInputFragmentDirections.showPokemonDetails(it)
            )
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(
            context,
            "Invalid input",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}