package com.chanel.android.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chanel.android.pokedex.databinding.FragmentPokemonListBinding
import com.chanel.android.pokedex.helpers.Event
import com.chanel.android.pokedex.model.Pokemon
import kotlinx.coroutines.launch

private const val NUM_COLUMNS = 2

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access FragmentPokemonListBinding because it is null. Is the view visible?"
        }

    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater)
        binding.pokemonListRecyclerView.layoutManager = GridLayoutManager(context, NUM_COLUMNS)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListViewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            onPokemonListChangedEvent(pokemonList)
        }

        pokemonListViewModel.getPokemonList()
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter(listOf())

    }

    private fun onPokemonListChangedEvent(pokemonList: List<Pokemon>) {
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter(pokemonList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}