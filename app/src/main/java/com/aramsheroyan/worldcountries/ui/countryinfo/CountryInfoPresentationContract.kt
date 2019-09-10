package com.aramsheroyan.worldcountries.ui.countryinfo

import com.aramsheroyan.worldcountries.data.room.Country

interface CountryInfoPresentationContract {
    interface View {
        fun showCountryDetails(country: Country)
    }

    interface Presenter {
        fun onGetCountryDetails(name: String)
        fun onDestroyed()
    }
}
