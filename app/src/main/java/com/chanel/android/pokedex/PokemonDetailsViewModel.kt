package com.chanel.android.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chanel.android.pokedex.model.FlavorTextEntry
import com.chanel.android.pokedex.network.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

private const val TAG = "PokemonDetailsViewModel"

class PokemonDetailsViewModel : ViewModel() {

    private var flavorTextEntries = listOf<FlavorTextEntry>()
    private var _flavorText = MutableLiveData<String>()
    val flavorText: LiveData<String>
        get() = _flavorText

    private val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getPokemonSpecies(id: Int) {
        RetrofitInstance.pokemonApi.getPokemonSpecies(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    flavorTextEntries = it.textEntries
                },
                onComplete = {
                    val text = flavorTextEntries.first().flavorText
                    _flavorText.postValue(text)
                },
                onError = {
                    Log.d(TAG, "Error getting pokemon species ${it.message}")
                }
            )
            .addTo(disposables)
    }
}