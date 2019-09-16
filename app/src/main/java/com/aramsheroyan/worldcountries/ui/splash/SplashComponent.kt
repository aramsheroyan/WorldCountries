/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui.splash

import com.aramsheroyan.worldcountries.app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface SplashComponent {
    fun inject(target: SplashFragment)
}