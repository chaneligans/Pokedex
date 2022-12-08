package com.chanel.android.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanel.android.pokedex.databinding.FragmentPokemonListBinding
import com.chanel.android.pokedex.model.Pokemon

private const val NUM_COLUMNS = 2

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access FragmentPokemonListBinding because it is null. Is the view visible?"
        }

    private lateinit var adapter: PokemonListAdapter
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

        // Make network call to retrieve initial pokemon
        pokemonListViewModel.getPokemonList()

        // Observe any changes to our list of pokemon
        pokemonListViewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            onPokemonListChangedEvent(pokemonList)
        }

        // Initialize adapter and add onclick method
        adapter = PokemonListAdapter { pokemon ->
            findNavController().navigate(SinglePokemonInputFragmentDirections.showPokemonDetails(pokemon))
        }
        binding.pokemonListRecyclerView.adapter = adapter

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) &&
                    !pokemonListViewModel.isLoading.get()
                ) {
                    pokemonListViewModel.getPokemonList()
                }
            }
        }
        binding.pokemonListRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun onPokemonListChangedEvent(pokemonList: List<Pokemon>) {
        adapter.submitList(pokemonList)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}