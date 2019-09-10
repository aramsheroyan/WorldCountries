package com.aramsheroyan.worldcountries.domain.dailyprogram

import com.aramsheroyan.worldcountries.data.room.Country
import com.aramsheroyan.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe

interface DailyProgramDomainContract {
    interface UseCase{
        fun getDailyCountries():Maybe<List<Country>>
        fun getInProgressCountries():Maybe<List<ProgramCountry>>

    }
}