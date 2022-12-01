package com.chanel.android.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "PokedexViewModel"

class PokedexViewModel : ViewModel() {

    val pokemon = MutableLiveData<Pokemon>()

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
                response.body()?.let {
                    pokemon.postValue(response.body())
                }
            } else {
                Log.e(TAG, "Response unsuccessful")
            }

        }
    }
    
}