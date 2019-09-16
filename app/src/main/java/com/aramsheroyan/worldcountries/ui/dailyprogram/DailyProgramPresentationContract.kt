/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.dailyprogram

import com.aramsheroyan.worldcountries.data.room.Country

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