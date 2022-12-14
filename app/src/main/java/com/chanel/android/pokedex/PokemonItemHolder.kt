package com.chanel.android.pokedex

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chanel.android.pokedex.databinding.PokemonItemBinding
import com.chanel.android.pokedex.helpers.Helper.getPokemonImageUrl
import com.chanel.android.pokedex.helpers.Helper.getTypeColor
import com.chanel.android.pokedex.helpers.Helper.getTypeColorPastel
import com.chanel.android.pokedex.helpers.Helper.getValidId
import com.chanel.android.pokedex.model.Pokemon
import java.util.Locale

class PokemonItemHolder(
    private val binding: PokemonItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pokemon: Pokemon,
        onPokemonClicked: (Pokemon) -> Unit
    ) {
        binding.apply {
            val context = pokemonNameText.context

            // Clear Pokemon type layout
            pokemonInfoLayout.removeAllViews()

            // Load Pokemon image in
            val validId = getValidId(pokemon.id)
            val imageUrl = getPokemonImageUrl(pokemon.id)
            pokemonImage.load(imageUrl) {
                placeholder(R.drawable.ic_baseline_downloading_24)
            }
            val backgroundColor = ContextCompat.getColor(context, getTypeColorPastel(pokemon.types.first().typeResult.name))
            pokemonImage.setBackgroundColor(backgroundColor)

            // Set ID and name
            pokemonIdText.text = "#$validId"
            pokemonNameText.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }

            // Create textviews for each type and add to view
            pokemon.types.forEach { type ->
                // Create text view and add margins/padding
                val textView = TextView(context)
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
                val backgroundColor = ContextCompat.getColor(context, getTypeColor(type.typeResult.name))
                textView.setBackgroundColor(backgroundColor)

                // Add to layout
                pokemonInfoLayout.addView(textView)
            }

            // Add onClick
            binding.root.setOnClickListener {
                onPokemonClicked(pokemon)
            }
        }
    }

}