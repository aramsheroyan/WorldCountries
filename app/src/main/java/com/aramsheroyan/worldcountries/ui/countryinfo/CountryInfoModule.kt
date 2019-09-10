package com.aramsheroyan.worldcountries.ui.countryinfo

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import dagger.Module
import dagger.Provides

@Module
class CountryInfoModule(val view: CountryInfoPresentationContract.View) {

    @Provides
    fun providePresenter(repository: CountriesDataContract.Repository): CountryInfoPresentationContract.Presenter {
        return CountryInfoPresenter(view, repository)
    }
}