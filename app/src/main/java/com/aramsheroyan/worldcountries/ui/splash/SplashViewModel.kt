/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.countries.CountriesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel(private val countriesRepository: CountriesDataContract.Repository) :
    ViewModel() {


    val state = MutableLiveData<State>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchCountries() {
        compositeDisposable.add(countriesRepository.getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state.value = State.OnSuccess
            },{
                state.value = State.OnError
            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    sealed class State {
        object OnSuccess : State()
        object OnError : State()
    }

    class Factory @Inject constructor(private val countriesRepository: CountriesDataContract.Repository) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            SplashViewModel(countriesRepository) as T

    }
}
