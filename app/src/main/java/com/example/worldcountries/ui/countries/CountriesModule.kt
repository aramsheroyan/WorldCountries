package com.example.worldcountries.ui.countries

import com.example.worldcountries.data.countries.CountriesDataContract
import dagger.Module
import dagger.Provides

@Module
class CountriesModule(private val view: CountriesPresentationContract.View) {

    @Provides
    fun providePresneter(repository: CountriesDataContract.Repository): CountriesPresentationContract.Presenter{
        return CountriesPresenter(view, repository)}
}