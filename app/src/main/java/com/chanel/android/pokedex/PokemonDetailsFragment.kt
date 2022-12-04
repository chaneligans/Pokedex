package com.chanel.android.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.chanel.android.pokedex.databinding.FragmentPokemonDetailsBinding
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_id_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_image
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_name_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.type_row

class PokemonDetailsFragment: Fragment() {

    // Pokemon passed from other Fragment
    private val args: PokemonDetailsFragmentArgs by navArgs()
    private val pokemon
        get() = args.pokemon

    private var _binding: FragmentPokemonDetailsBinding? = null
    val binding
        get() = checkNotNull(_binding) {
        "Cannot access FragmentPokemonDetailsBinding because it is null. Is the view visible?"
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
            val validId = validateId(pokemon.id.toString())
            val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$validId.png"
            pokemon_image.load(imageUrl)
            pokemon_id_text.text = validId
            pokemon_name_text.text = pokemon.name
            pokemon.types.forEach { type ->
                val textView = TextView(context)
                textView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textView.text = type.typeResult.name
                type_row.addView(textView)
            }
        }
    }

    private fun validateId(id: String): String {
        val zeroesToAdd = 3 - id.length
        var zeroes = ""
        for (i in 0 until zeroesToAdd) {
            zeroes += "0"
        }
        return "$zeroes$id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}