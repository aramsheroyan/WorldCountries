/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.dailyprogram

import com.aramsheroyan.worldcountries.data.room.ProgramCountry
import io.reactivex.Completable
import io.reactivex.Maybe

interface CapitalsProgramDataContract {
    interface Repository{
        fun save(programCountries: List<ProgramCountry>)
        fun getInProgressCountries(): Maybe<List<ProgramCountry>>
        fun updateInProgressItems(): Completable
    }

    interface LocalDataSource{
        fun save(programCountries: List<ProgramCountry>)
        fun getInProgressCountries(): Maybe<List<ProgramCountry>>
        fun updateInProgressItems(): Completable
    }
}