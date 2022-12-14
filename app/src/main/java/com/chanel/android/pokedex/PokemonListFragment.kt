package com.chanel.android.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chanel.android.pokedex.databinding.FragmentPokemonListBinding
import com.chanel.android.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_list.progress_bar

private const val NUM_COLUMNS = 2

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    val binding
        get() = checkNotNull(_binding) {
            "Cannot access FragmentPokemonListBinding because it is null. Is the view visible?"
        }

    private lateinit var adapter: PokemonListAdapter
    private val pokemonListViewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        // Observe if we are loading to show the loading spinner
        pokemonListViewModel.isLoadingObservable.observe(viewLifecycleOwner) { isLoading ->
            onLoadingStatusChangedEvent(isLoading)
        }

        // Initialize adapter and add onclick method
        adapter = PokemonListAdapter { pokemon ->
            findNavController().navigate(SinglePokemonInputFragmentDirections.showPokemonDetails(pokemon))
        }
        binding.pokemonListRecyclerView.adapter = adapter

        // Loads more pokemon when we scroll to the bottom of the recyclerview
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val newList = pokemonListViewModel.pokemonList.value?.filter {
                it.name.contains(query) || it.id.toString().contains(query)
            }
            newList?.let {
                onPokemonListChangedEvent(it)
            }
        } else {
            pokemonListViewModel.pokemonList.value?.let {
                onPokemonListChangedEvent(it)
            }
        }
    }

    private fun onPokemonListChangedEvent(pokemonList: List<Pokemon>) {
        adapter.submitList(pokemonList)
    }

    private fun onLoadingStatusChangedEvent(isLoading: Boolean) {
        progress_bar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}