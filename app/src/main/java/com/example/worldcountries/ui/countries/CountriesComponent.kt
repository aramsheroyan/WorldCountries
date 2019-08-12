package com.example.worldcountries.ui.countries

import com.example.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CountriesModule::class])
interface CountriesComponent {
    fun inject(target: CountriesFragment)
}