package com.example.worldcountries.ui.countries

import com.example.worldcountries.data.room.Country


interface CountriesPresentationContract {

    interface Presenter{
        fun onScreenStarted()
    }

    interface View{
        fun populateData(countries: List<Country>)
    }
}