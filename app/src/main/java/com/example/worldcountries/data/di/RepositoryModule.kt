package com.example.worldcountries.data.di

import com.example.worldcountries.data.countries.*
import com.example.worldcountries.data.room.Country
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCountriesRemoteDataSource(countriesAPI: CountriesAPI): CountriesDataContract.RemoteDataSource {
        return CountriesRemoteDataSource(countriesAPI)
    }

    @Provides
    fun provideCountriesLocalDataSource(countriesDAO: Country.CountriesDAO): CountriesDataContract.LocalDataSource {
        return CountriesLocalDataSource(countriesDAO)
    }

    @Provides
    fun provideCountriesRepository(remoteDataSource: CountriesDataContract.RemoteDataSource,localDataSource: CountriesDataContract.LocalDataSource):
            CountriesDataContract.Repository {
        return CountriesRepository(remoteDataSource,localDataSource)
    }
}