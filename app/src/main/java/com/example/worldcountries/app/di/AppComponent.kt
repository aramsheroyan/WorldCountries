package com.example.worldcountries.app.di

import android.app.Application
import com.example.worldcountries.app.WCApplication
import com.example.worldcountries.data.di.DatabaseModule
import com.example.worldcountries.data.di.RepositoryModule
import com.example.worldcountries.ui.countries.CountriesComponent
import com.example.worldcountries.ui.countries.CountriesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(target: WCApplication)

    fun createSubComponent(module: CountriesModule): CountriesComponent
}