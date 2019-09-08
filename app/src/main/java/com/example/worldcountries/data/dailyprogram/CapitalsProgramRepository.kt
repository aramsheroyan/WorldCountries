package com.example.worldcountries.data.dailyprogram

import com.example.worldcountries.data.room.ProgramCountry
import io.reactivex.Maybe
import io.reactivex.Single

class CapitalsProgramRepository(
    private val localDataSource: CapitalsProgramDataContract.LocalDataSource
) : CapitalsProgramDataContract.Repository {

    override fun save(programCountries: List<ProgramCountry>) {
        localDataSource.save(programCountries)
    }

    override fun getInProgressCountries(): Maybe<List<ProgramCountry>> {
        return localDataSource.getInProgressCountries()
    }
}