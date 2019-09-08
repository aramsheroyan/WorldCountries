package com.example.worldcountries.ui.dailyprogram

import com.example.worldcountries.data.countries.CountriesDataContract
import com.example.worldcountries.data.countries.CountriesRepository
import com.example.worldcountries.domain.dailyprogram.DailyProgramDomainContract
import com.example.worldcountries.domain.dailyprogram.DailyProgramUseCase
import dagger.Module
import dagger.Provides

@Module
class DailyProgramModule(
    private val view: DailyProgramPresentationContract.View
) {

    @Provides
    fun providePresenter(dailyProgramUseCase: DailyProgramDomainContract.UseCase): DailyProgramPresentationContract.Presenter {
        return DailyProgramPresenter(view, dailyProgramUseCase)
    }
}