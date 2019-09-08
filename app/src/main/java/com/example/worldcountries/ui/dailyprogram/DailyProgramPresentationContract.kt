package com.example.worldcountries.ui.dailyprogram

import com.example.worldcountries.data.room.Country

interface DailyProgramPresentationContract {
    interface View{
        fun setCountries(countries: List<Country>)
        fun navigateToQuizScreen()
    }
    
    interface Presenter{
        fun onScreenStarted()
        fun onDestroy()
    }
} 