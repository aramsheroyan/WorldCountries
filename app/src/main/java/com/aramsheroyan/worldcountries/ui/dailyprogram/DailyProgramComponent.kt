package com.aramsheroyan.worldcountries.ui.dailyprogram

import com.aramsheroyan.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DailyProgramModule::class])
interface DailyProgramComponent {
    fun inject(target: DailyProgramFragment)
}