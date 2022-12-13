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
import com.chanel.android.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_details.back_default_sprite
import kotlinx.android.synthetic.main.fragment_pokemon_details.back_shiny_sprite
import kotlinx.android.synthetic.main.fragment_pokemon_details.front_default_sprite
import kotlinx.android.synthetic.main.fragment_pokemon_details.front_shiny_sprite
import kotlinx.android.synthetic.main.fragment_pokemon_details.height
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_atk_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_def_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_exp_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_height_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_hp_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_id_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_image
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_name_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_spd_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.pokemon_weight_text
import kotlinx.android.synthetic.main.fragment_pokemon_details.type_row
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
            // Load image in
            val validId = getValidId(pokemon.id)
            val imageUrl = getPokemonImageUrl(pokemon.id)
            pokemon_image.load(imageUrl) {
                placeholder(R.drawable.ic_baseline_downloading_24)
            }

            // Set name and id textviews
            pokemon_id_text.text = "#$validId"
            pokemon_name_text.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }

            // Create textviews for each type and add to view
            setTypes(pokemon)

            pokemon_height_text.text = getHeightMetersString(pokemon.height)
            pokemon_weight_text.text = getWeightKgString(pokemon.weight)
            pokemon_exp_text.text = pokemon.baseExperience.toString()

            setStats(pokemon)

            setSprites(pokemon)
        }
    }

    private fun setTypes(pokemon: Pokemon) {
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
            type_row.addView(textView)
        }
    }

    private fun setStats(pokemon: Pokemon) {
        pokemon.stats.forEach { pokemonStat ->
            val value = pokemonStat.baseStat.toString()
            when (pokemonStat.stat.name) {
                "hp" -> pokemon_hp_text.text = value
                "attack" -> pokemon_atk_text.text = value
                "defense" -> pokemon_def_text.text = value
                "speed" -> pokemon_spd_text.text = value
            }
        }
    }

    private fun setSprites(pokemon: Pokemon) {
        front_default_sprite.load(pokemon.sprites.front_default_url) {
            placeholder(R.drawable.ic_baseline_downloading_24)
        }
        front_shiny_sprite.load(pokemon.sprites.front_shiny_url) {
            placeholder(R.drawable.ic_baseline_downloading_24)
        }
        back_default_sprite.load(pokemon.sprites.back_default_url) {
            placeholder(R.drawable.ic_baseline_downloading_24)
        }
        back_shiny_sprite.load(pokemon.sprites.back_shiny_url) {
            placeholder(R.drawable.ic_baseline_downloading_24)
        }
    }

    private fun getHeightMetersString(height: Int): String {
        val h = String.format("%.2f", (height * .1))
        return "$h m"
    }

    private fun getWeightKgString(weight: Int): String {
        val w = String.format("%.2f", (weight * .1))
        return "$w kg"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}