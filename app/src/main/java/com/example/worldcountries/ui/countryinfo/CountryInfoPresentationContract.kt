package com.example.worldcountries.ui.countryinfo

import com.example.worldcountries.data.room.Country

interface CountryInfoPresentationContract {
    interface View {
        fun showCountryDetails(country: Country)
    }

    interface Presenter {
        fun onGetCountryDetails(name: String)
        fun onDestroyed()
    }
}
