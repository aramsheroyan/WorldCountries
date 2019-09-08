package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import io.reactivex.Observable
import io.reactivex.Single

class CountriesRemoteDataSource(private val countriesAPI: CountriesAPI) :
    CountriesDataContract.RemoteDataSource {

    override fun getAllCountries(): Single<List<Country>> {
        return countriesAPI.getAll()
            .flattenAsObservable { it }
            .filter {
                it.capital != ""
            }
            .toList()
    }

}