package com.aramsheroyan.worldcountries.ui.countries

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import dagger.Module
import dagger.Provides

@Module
class CountriesModule(private val view: CountriesPresentationContract.View) {

    @Provides
    fun providePresneter(repository: CountriesDataContract.Repository): CountriesPresentationContract.Presenter{
        return CountriesPresenter(view, repository)}
}