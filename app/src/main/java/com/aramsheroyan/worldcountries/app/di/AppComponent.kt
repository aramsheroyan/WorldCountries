/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.app.di

import android.app.Application
import com.aramsheroyan.worldcountries.app.WCApplication
import com.aramsheroyan.worldcountries.data.di.DatabaseModule
import com.aramsheroyan.worldcountries.data.di.RepositoryModule
import com.aramsheroyan.worldcountries.domain.di.DomainModule
import com.aramsheroyan.worldcountries.ui.countries.CountriesComponent
import com.aramsheroyan.worldcountries.ui.countries.CountriesModule
import com.aramsheroyan.worldcountries.ui.countryinfo.CountryInfoComponent
import com.aramsheroyan.worldcountries.ui.countryinfo.CountryInfoModule
import com.aramsheroyan.worldcountries.ui.dailyprogram.DailyProgramComponent
import com.aramsheroyan.worldcountries.ui.dailyprogram.DailyProgramModule
import com.aramsheroyan.worldcountries.ui.quiz.QuizComponent
import com.aramsheroyan.worldcountries.ui.quiz.QuizModule
import com.aramsheroyan.worldcountries.ui.splash.SplashComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, DatabaseModule::class, DomainModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(target: WCApplication)

    fun createSubComponent(module: CountriesModule): CountriesComponent
    fun createSubComponent(module: CountryInfoModule): CountryInfoComponent
    fun createSubComponent(module: QuizModule): QuizComponent
    fun createSubComponent(module: DailyProgramModule): DailyProgramComponent
    fun createSubComponent(): SplashComponent
}