package com.aramsheroyan.worldcountries.ui.countryinfo

import com.aramsheroyan.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CountryInfoModule::class])
interface CountryInfoComponent {
    fun inject(target: CountryInfoFragment)
}