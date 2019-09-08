package com.example.worldcountries.domain.di

import com.example.worldcountries.data.countries.CountriesDataContract
import com.example.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import com.example.worldcountries.domain.dailyprogram.DailyProgramDomainContract
import com.example.worldcountries.domain.dailyprogram.DailyProgramUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideDailyProgramUseCase(
        countriesRepository: CountriesDataContract.Repository,
        capitalsProgramRepository: CapitalsProgramDataContract.Repository
    )
            : DailyProgramDomainContract.UseCase {
        return DailyProgramUseCase(countriesRepository, capitalsProgramRepository)
    }

}