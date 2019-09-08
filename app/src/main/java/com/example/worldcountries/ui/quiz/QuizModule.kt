package com.example.worldcountries.ui.quiz

import com.example.worldcountries.data.countries.CountriesDataContract
import dagger.Module
import dagger.Provides

@Module
class QuizModule(val view: QuizPresentationContract.View) {

    @Provides
    fun providePresenter(countriesRepository: CountriesDataContract.Repository): QuizPresentationContract.Presenter {
        return QuizPresenter(view,countriesRepository)
    }
}