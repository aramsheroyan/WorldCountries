/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.countries

import com.aramsheroyan.worldcountries.data.room.Country
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