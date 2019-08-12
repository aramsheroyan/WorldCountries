package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import com.example.worldcountries.data.room.Country.CountriesDAO
import io.reactivex.Maybe
import io.reactivex.Single

class CountriesLocalDataSource(val countriesDAO: CountriesDAO): CountriesDataContract.LocalDataSource {
    override fun getAllCountries(): Single<List<Country>> {
        return countriesDAO.getAll()

    }

    override fun save(countries: List<Country>) {
        countriesDAO.insertAll(*countries.toTypedArray())
    }
}