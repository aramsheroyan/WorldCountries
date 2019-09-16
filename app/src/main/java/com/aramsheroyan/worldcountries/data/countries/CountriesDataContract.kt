/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.countries

import com.aramsheroyan.worldcountries.data.room.Country
import io.reactivex.Maybe
import io.reactivex.Single

interface CountriesDataContract {

    interface Repository {
        fun getAllCountries(): Single<List<Country>>
        fun getCountryByName(name: String): Maybe<Country>
        fun getRandom(amount: Int): Maybe<List<Country>>
        fun getDailyCountries(): Maybe<List<Country>>
        fun getCapitalsProgramInProgress(): Maybe<List<Country>>
        fun getLearnedCountries(amount: Int):Maybe<List<Country>>
        fun getAllLearnedCountries(): Maybe<List<Country>>
    }

    interface RemoteDataSource {
        fun getAllCountries(): Single<List<Country>>
    }

    interface LocalDataSource {
        fun getAllCountries(): Single<List<Country>>
        fun getCountryByName(name: String): Maybe<Country>
        fun save(countries: List<Country>)
        fun getRandom(amount: Int): Maybe<List<Country>>
        fun getDailyCountries(): Maybe<List<Country>>
        fun getCapitalsProgramInProgress(): Maybe<List<Country>>
        fun getLearnedCountries(amount: Int):Maybe<List<Country>>
        fun getAllLearnedCountries(): Maybe<List<Country>>
    }
}