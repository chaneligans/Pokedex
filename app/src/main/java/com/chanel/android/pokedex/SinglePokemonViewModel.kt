package com.chanel.android.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanel.android.pokedex.helpers.Event
import com.chanel.android.pokedex.model.Pokemon
import com.chanel.android.pokedex.network.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

private const val TAG = "PokedexViewModel"

class SinglePokemonViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _pokemon = MutableLiveData<Event<Pokemon>>()
    val pokemon: LiveData<Event<Pokemon>>
        get() = _pokemon

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getSinglePokemon(id: String) {
        RetrofitInstance.pokemonApi.getPokemonInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { response ->
                    _pokemon.value = Event(response)
                },
                onError = {
                    Log.e(TAG, "Error retrieving data: ${it.message}")
                }
            ).addTo(disposables)
    }
}