package com.example.worldcountries.data.dailyprogram

import com.example.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe
import io.reactivex.Single

interface CapitalsProgramDataContract {
    interface Repository{
        fun save(programCountries: List<ProgramCountry>)
        fun getInProgressCountries(): Maybe<List<ProgramCountry>>

    }

    interface LocalDataSource{
        fun save(programCountries: List<ProgramCountry>)
        fun getInProgressCountries(): Maybe<List<ProgramCountry>>
    }
}