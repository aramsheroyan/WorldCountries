package com.example.worldcountries.ui.dailyprogram

import com.example.worldcountries.data.countries.CountriesDataContract
import com.example.worldcountries.domain.dailyprogram.DailyProgramDomainContract
import com.example.worldcountries.domain.dailyprogram.DailyProgramUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class DailyProgramPresenter(
    private val view: DailyProgramPresentationContract.View,
    private val dailyProgramUseCase: DailyProgramDomainContract.UseCase
) : DailyProgramPresentationContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onScreenStarted() {
        compositeDisposable.add(
            dailyProgramUseCase.getDailyCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        view.setCountries(it)
                }, {
                    Timber.e(it)
                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}