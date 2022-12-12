package com.chanel.android.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.chanel.android.pokedex.databinding.FragmentPokemonDetailsBinding
import com.chanel.android.pokedex.helpers.Helper
import com.chanel.android.pokedex.helpers.Helper.getPokemonImageUrl
import com.chanel.android.pokedex.helpers.Helper.getValidId
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_id_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_image
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_name_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.info_row
import java.util.Locale

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
            val validId = getValidId(pokemon.id)
            val imageUrl = getPokemonImageUrl(pokemon.id)
            pokemon_image.load(imageUrl)

            pokemon_id_text.text = "#$validId"
            pokemon_name_text.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }

            // Create textviews for each type and add to view
            pokemon.types.forEach { type ->
                // Create text view and add margins/padding
                val textView = TextView(pokemon_name_text.context)
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(5)
                textView.layoutParams = layoutParams
                textView.setPadding(20, 5, 20, 5)

                // Set text with first letter capitalized
                textView.text = type.typeResult.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }

                // Add style
                textView.setTextAppearance(R.style.card_type_text)
                val backgroundColor = ContextCompat.getColor(
                    pokemon_name_text.context,
                    Helper.getTypeColor(type.typeResult.name)
                )
                textView.setBackgroundColor(backgroundColor)

                // Add to layout
                info_row.addView(textView)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}