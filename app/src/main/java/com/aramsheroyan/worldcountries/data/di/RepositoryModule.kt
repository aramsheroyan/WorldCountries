/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.di

import com.aramsheroyan.worldcountries.data.countries.*
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramLocalDataSource
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramRepository
import com.aramsheroyan.worldcountries.data.room.Country
import com.aramsheroyan.worldcountries.data.room.ProgramCountry
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
    fun provideCountriesRepository(
        remoteDataSource: CountriesDataContract.RemoteDataSource,
        localDataSource: CountriesDataContract.LocalDataSource
    ): CountriesDataContract.Repository {
        return CountriesRepository(remoteDataSource, localDataSource)
    }

    @Provides
    fun provideDailyProgramLocalDataSource(capitalsProgramDAO: ProgramCountry.CapitalsProgramDAO): CapitalsProgramDataContract.LocalDataSource {
        return CapitalsProgramLocalDataSource(capitalsProgramDAO)
    }

    @Provides
    fun provideDailyPrgramRepository(localDataSource: CapitalsProgramDataContract.LocalDataSource):
            CapitalsProgramDataContract.Repository {
        return CapitalsProgramRepository(localDataSource)
    }
}