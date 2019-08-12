package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import io.reactivex.Single

class CountriesRemoteDataSource(private val countriesAPI: CountriesAPI): CountriesDataContract.RemoteDataSource {

    override fun getAllCountries(): Single<List<Country>> {
        return countriesAPI.getAll()
    }

}