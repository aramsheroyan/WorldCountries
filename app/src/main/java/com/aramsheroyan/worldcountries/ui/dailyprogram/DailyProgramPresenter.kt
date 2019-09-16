/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.dailyprogram

import com.aramsheroyan.worldcountries.domain.dailyprogram.DailyProgramDomainContract
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