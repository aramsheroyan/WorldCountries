package com.example.worldcountries.ui.countryinfo

import com.example.worldcountries.data.countries.CountriesDataContract

class CountryInfoPresenter(
    val view: CountryInfoPresentationContract.View,
    val repository: CountriesDataContract.Repository
) : CountryInfoPresentationContract.Presenter {
}