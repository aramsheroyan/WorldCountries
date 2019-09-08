package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface CountriesDataContract {

    interface Repository {
        fun getAllCountries(): Single<List<Country>>
        fun getCountryByName(name: String): Maybe<Country>
        fun getRandom(amount: Int): Maybe<List<Country>>
        fun getDailyCountries():Maybe<List<Country>>

    }

    interface RemoteDataSource {
        fun getAllCountries(): Single<List<Country>>
    }

    interface LocalDataSource {
        fun getAllCountries(): Single<List<Country>>
        fun getCountryByName(name: String): Maybe<Country>
        fun save(countries: List<Country>)
        fun getRandom(amount: Int): Maybe<List<Country>>
        fun getDailyCountries():Maybe<List<Country>>
    }
}