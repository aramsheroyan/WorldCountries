/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.dailyprogram

import com.aramsheroyan.worldcountries.data.room.ProgramCountry
import io.reactivex.Completable
import io.reactivex.Maybe

class CapitalsProgramLocalDataSource(
    private val capitalsProgramDAO: ProgramCountry.CapitalsProgramDAO
) : CapitalsProgramDataContract.LocalDataSource {

    override fun save(programCountries: List<ProgramCountry>) {
        capitalsProgramDAO.insertAll(*programCountries.toTypedArray())
    }

    override fun getInProgressCountries(): Maybe<List<ProgramCountry>> {
        return Maybe.fromCallable{
            val items = capitalsProgramDAO.getAllInProgressItems()
            if(items.isNotEmpty()) items else listOf()

        }
    }

    override fun updateInProgressItems(): Completable {
        return capitalsProgramDAO.updateInProgressItems()
    }
}