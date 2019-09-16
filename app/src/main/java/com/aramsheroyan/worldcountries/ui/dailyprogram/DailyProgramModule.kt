/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.dailyprogram

import com.aramsheroyan.worldcountries.domain.dailyprogram.DailyProgramDomainContract
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