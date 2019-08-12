package com.example.worldcountries.ui.countryinfo

import com.example.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CountryInfoModule::class])
interface CountryInfoComponent {
    fun inject(target: CountryInfoFragment)
}