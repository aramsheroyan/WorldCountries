package com.example.worldcountries.domain.dailyprogram

import com.example.worldcountries.data.room.Country
import com.example.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe

interface DailyProgramDomainContract {
    interface UseCase{
        fun getDailyCountries():Maybe<List<Country>>
        fun getInProgressCountries():Maybe<List<ProgramCountry>>

    }
}