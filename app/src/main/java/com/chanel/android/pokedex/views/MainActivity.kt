package com.chanel.android.pokedex.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.chanel.android.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemon.observe(this@MainActivity) { pokemon ->
            Log.d("chanelz", "$pokemon")
        }

        binding.generateButton.setOnClickListener {
            val pokemonInput = binding.pokemonInput.text.toString()
            viewModel.getSinglePokemon(pokemonInput)
        }
    }
}