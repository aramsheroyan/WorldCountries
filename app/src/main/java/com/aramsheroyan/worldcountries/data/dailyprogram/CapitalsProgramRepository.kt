/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.dailyprogram

import com.aramsheroyan.worldcountries.data.room.ProgramCountry
import io.reactivex.Completable
import io.reactivex.Maybe

class CapitalsProgramRepository(
    private val localDataSource: CapitalsProgramDataContract.LocalDataSource
) : CapitalsProgramDataContract.Repository {

    override fun save(programCountries: List<ProgramCountry>) {
        localDataSource.save(programCountries)
    }

    override fun getInProgressCountries(): Maybe<List<ProgramCountry>> {
        return localDataSource.getInProgressCountries()
    }

    override fun updateInProgressItems(): Completable {
        return localDataSource.updateInProgressItems()
    }
}