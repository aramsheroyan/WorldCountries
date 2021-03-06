/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.countries

import com.aramsheroyan.worldcountries.data.room.Country
import com.aramsheroyan.worldcountries.data.room.Country.CountriesDAO
import io.reactivex.Maybe
import io.reactivex.Single
import timber.log.Timber

class CountriesLocalDataSource(val countriesDAO: CountriesDAO) :
    CountriesDataContract.LocalDataSource {
    override fun getAllCountries(): Single<List<Country>> {
        return countriesDAO.getAll()

    }

    override fun getCountryByName(name: String): Maybe<Country> {
        return countriesDAO.getByName(name)
    }

    override fun save(countries: List<Country>) {
        countriesDAO.insertAll(*countries.toTypedArray())
    }

    override fun getRandom(amount: Int): Maybe<List<Country>> {
        return countriesDAO.getRandom(amount)
    }

    override fun getDailyCountries(): Maybe<List<Country>> {
        return countriesDAO.getDailyRandom()
            .doAfterSuccess {
                Timber.d(it.toString())
            }
    }

    override fun getCapitalsProgramInProgress(): Maybe<List<Country>> {
        return countriesDAO.getCapitalProgramInProgress()
    }

    override fun getLearnedCountries(amount: Int): Maybe<List<Country>> {
        return countriesDAO.getLearnedCountries(amount)
    }

    override fun getAllLearnedCountries(): Maybe<List<Country>> {
        return countriesDAO.getAllLearnedCountries()
    }
}