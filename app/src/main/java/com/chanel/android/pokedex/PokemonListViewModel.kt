package com.chanel.android.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanel.android.pokedex.model.Pokemon
import com.chanel.android.pokedex.network.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

private const val TAG = "PokemonListViewModel"
private const val RESULT_LIMIT = 20

class PokemonListViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    private var pokemonResults = mutableListOf<Pokemon>()
    private var _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList
    private var currentPosition = 0
    private var totalResultCount: Int? = null

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getPokemonList() {
        RetrofitInstance.pokemonApi.getPokemonList(
            offset = currentPosition,
            limit = RESULT_LIMIT
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { pokemonListQueryResult ->
                totalResultCount = pokemonListQueryResult.count
                Log.d("chanelz", "1: $pokemonListQueryResult")
                pokemonListQueryResult.results
            }
            .flatMap { pokemonQueryResult ->
                Log.d("chanelz", "1: $pokemonQueryResult")
                RetrofitInstance.pokemonApi.getPokemonInfo(pokemonQueryResult.name)
            }
            .subscribeBy(
                onNext = { pokemon ->
                    Log.d("chanelz", "adding pokemon ${pokemon.name}")
                    pokemonResults.add(pokemon)
                    currentPosition++
                },
                onComplete = {
                    Log.d("chanelz", "added $currentPosition pokemon ")
                    _pokemonList.postValue(pokemonResults)
                },
                onError = {
                    Log.d("chanelz", "error: ${it}")
                }
            )
            .addTo(disposables)
    }
}