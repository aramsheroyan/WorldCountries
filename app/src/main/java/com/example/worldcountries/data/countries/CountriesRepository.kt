package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
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

}

