package com.aramsheroyan.worldcountries.ui.countries

import com.aramsheroyan.worldcountries.data.room.Country


interface CountriesPresentationContract {

    interface Presenter{
        fun onScreenStarted()
        fun onDestroyed()
    }

    interface View{
        fun populateData(countries: List<Country>)
    }
}