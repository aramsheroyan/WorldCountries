package com.example.worldcountries.ui.countries

import com.example.worldcountries.data.countries.CountriesDataContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CountriesPresenter(
    private val view: CountriesPresentationContract.View,
    private val countriesRepository: CountriesDataContract.Repository
) : CountriesPresentationContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onScreenStarted() {
        compositeDisposable.add(
            countriesRepository.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({countries->
                    view.populateData(countries)
                },{
                    t: Throwable? ->
                    Timber.e(t)

                })
        )
    }

}