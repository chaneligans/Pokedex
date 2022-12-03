package com.chanel.android.pokedex

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.chanel.android.pokedex.model.Pokemon
import com.chanel.android.pokedex.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "PokedexViewModel"

class SinglePokemonViewModel : ViewModel() {

    private val _pokemon = MutableLiveData<Event<Pokemon>>()
    val pokemon: LiveData<Event<Pokemon>>
        get() = _pokemon

    fun getSinglePokemon(id: String) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getPokemonInfo(id)
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch(e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    _pokemon.value = Event(body)
                }
            } else {
                Log.e(TAG, "Response unsuccessful")
            }

        }
    }
}