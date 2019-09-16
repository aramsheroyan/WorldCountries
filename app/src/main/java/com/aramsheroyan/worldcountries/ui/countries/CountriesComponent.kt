/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.countries

import com.aramsheroyan.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [CountriesModule::class])
interface CountriesComponent {
    fun inject(target: CountriesFragment)
}