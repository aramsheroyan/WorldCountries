/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.countries

import com.aramsheroyan.worldcountries.data.room.Country
import io.reactivex.Maybe
import io.reactivex.Single

class CountriesRepository(private val remoteDataSource: CountriesDataContract.RemoteDataSource,
                          private val localDataSource: CountriesDataContract.LocalDataSource) :
    CountriesDataContract.Repository{

    override fun getAllCountries(): Single<List<Country>> {
        var saveResponse = false
        return localDataSource.getAllCountries()
            .doOnSuccess {
               if(it.isEmpty())
                   saveResponse = true
            }
            .concatWith(remoteDataSource.getAllCountries())
            .filter {
                it.isNotEmpty()
            }
            .firstElement()
            .doOnSuccess {
                if(saveResponse){
                    localDataSource.save(it)
                }
            }
            .toSingle()
    }

    override fun getCountryByName(name: String): Maybe<Country> {
        return localDataSource.getCountryByName(name)
    }

    override fun getRandom(amount: Int): Maybe<List<Country>> {
        return localDataSource.getRandom(amount)
    }

    override fun getDailyCountries(): Maybe<List<Country>> {
        return localDataSource.getDailyCountries()
    }

    override fun getCapitalsProgramInProgress(): Maybe<List<Country>> {
        return localDataSource.getCapitalsProgramInProgress()
    }

    override fun getLearnedCountries(amount: Int): Maybe<List<Country>> {
        return  localDataSource.getLearnedCountries(amount)
    }

    override fun getAllLearnedCountries(): Maybe<List<Country>> {
        return localDataSource.getAllLearnedCountries()
    }
}

