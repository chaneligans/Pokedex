package com.chanel.android.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        pokemonListViewModel.getPokemonList()
        pokemonListViewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            onPokemonListChangedEvent(pokemonList)
        }
        onPokemonListChangedEvent(listOf())

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    pokemonListViewModel.getPokemonList()
                }
            }
        }
        binding.pokemonListRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun onPokemonListChangedEvent(pokemonList: List<Pokemon>) {
        binding.pokemonListRecyclerView.adapter = PokemonListAdapter(pokemonList) { pokemon ->
                findNavController().navigate(SinglePokemonInputFragmentDirections.showPokemonDetails(pokemon))
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}