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
import java.util.concurrent.atomic.AtomicBoolean

private const val TAG = "PokemonListViewModel"
private const val RESULT_LIMIT = 20

class PokemonListViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    private var _isLoading = AtomicBoolean(false)
    val isLoading
        get() = _isLoading
    private var _isLoadingObservable = MutableLiveData<Boolean>()
    val isLoadingObservable: LiveData<Boolean>
        get() = _isLoadingObservable
    private var pokemonResults = mutableListOf<Pokemon>()
    private var totalPokemon = mutableListOf<Pokemon>()
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
        totalResultCount?.let { count ->
            if (currentPosition >= count) return
        }

        _isLoading.set(true)
        _isLoadingObservable.postValue(true)
        RetrofitInstance.pokemonApi.getPokemonList(
            offset = currentPosition,
            limit = RESULT_LIMIT
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapIterable { pokemonListQueryResult ->
                totalResultCount = pokemonListQueryResult.count
                pokemonListQueryResult.results
            }
            .flatMap { pokemonQueryResult ->
                RetrofitInstance.pokemonApi.getPokemonInfo(pokemonQueryResult.name)
            }
            .subscribeBy(
                onNext = { pokemon ->
                    pokemonResults.add(pokemon)
                    currentPosition++
                },
                onComplete = {
                    val sortedPokemon = pokemonResults.sortedBy {pokemon -> pokemon.id}
                    pokemonResults.clear()
                    Log.d("chanelz", "added $sortedPokemon")
                    totalPokemon.addAll(sortedPokemon)
                    _pokemonList.postValue(totalPokemon)
                    _isLoading.set(false)
                    _isLoadingObservable.postValue(false)
                },
                onError = {
                    _isLoading.set(false)
                    Log.d(TAG, "Error while retrieving Pokemon list: $it")
                }
            )
            .addTo(disposables)
    }
}