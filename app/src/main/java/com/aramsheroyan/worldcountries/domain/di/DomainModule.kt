/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.domain.di

import com.aramsheroyan.worldcountries.data.countries.CountriesDataContract
import com.aramsheroyan.worldcountries.data.dailyprogram.CapitalsProgramDataContract
import com.aramsheroyan.worldcountries.domain.dailyprogram.DailyProgramDomainContract
import com.aramsheroyan.worldcountries.domain.dailyprogram.DailyProgramUseCase
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