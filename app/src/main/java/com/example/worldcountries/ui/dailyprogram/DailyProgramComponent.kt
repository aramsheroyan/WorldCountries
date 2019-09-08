package com.example.worldcountries.ui.dailyprogram

import com.example.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [DailyProgramModule::class])
interface DailyProgramComponent {
    fun inject(target: DailyProgramFragment)
}