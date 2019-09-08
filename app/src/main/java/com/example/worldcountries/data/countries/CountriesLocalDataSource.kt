package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import com.example.worldcountries.data.room.Country.CountriesDAO
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
}