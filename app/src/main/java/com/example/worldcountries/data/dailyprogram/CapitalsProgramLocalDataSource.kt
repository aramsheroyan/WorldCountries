package com.example.worldcountries.data.dailyprogram

import com.example.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe
import io.reactivex.Single
import timber.log.Timber

class CapitalsProgramLocalDataSource(
    private val capitalsProgramDAO: ProgramCountry.CapitalsProgramDAO
) : CapitalsProgramDataContract.LocalDataSource {

    override fun save(programCountries: List<ProgramCountry>) {
        capitalsProgramDAO.insertAll(*programCountries.toTypedArray())
    }

    override fun getInProgressCountries(): Maybe<List<ProgramCountry>> {

        return Maybe.fromCallable{
            val a = capitalsProgramDAO.getAllInProgressItems()
            Timber.d("AAAAAA" + a.isEmpty())
            if(a.isNotEmpty()) a else listOf()

        }
    }
}