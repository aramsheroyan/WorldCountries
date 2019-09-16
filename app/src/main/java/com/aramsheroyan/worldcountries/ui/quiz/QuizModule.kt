/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.quiz

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import dagger.Module
import dagger.Provides

@Module
class QuizModule(val view: QuizPresentationContract.View) {

    @Provides
    fun providePresenter(
        countriesRepository: CountriesDataContract.Repository,
        capitalsProgramRepository: CapitalsProgramDataContract.Repository
    ): QuizPresentationContract.Presenter {
        return QuizPresenter(view, countriesRepository, capitalsProgramRepository)
    }
}