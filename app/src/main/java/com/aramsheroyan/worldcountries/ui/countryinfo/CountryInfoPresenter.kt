/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countryinfo

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CountryInfoPresenter(
    val view: CountryInfoPresentationContract.View,
    val repository: CountriesDataContract.Repository
) : CountryInfoPresentationContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onGetCountryDetails(name: String) {
        compositeDisposable.add(
            repository.getCountryByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showCountryDetails(it)
                }, {
                    Timber.e(it)
                })
        )
    }

    override fun onDestroyed() {
        compositeDisposable.dispose()
    }
}