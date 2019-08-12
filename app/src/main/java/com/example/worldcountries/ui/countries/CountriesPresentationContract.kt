package com.example.worldcountries.ui.countries

import com.example.worldcountries.data.room.Country


interface CountriesPresentationContract {

    interface Presenter{
        fun onScreenStarted()
        fun onDestroyed()
    }

    interface View{
        fun populateData(countries: List<Country>)
    }
}