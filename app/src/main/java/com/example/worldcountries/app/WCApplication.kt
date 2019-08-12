package com.example.worldcountries.app

import android.app.Application
import com.example.worldcountries.BuildConfig
import com.example.worldcountries.app.di.AppComponent
import com.example.worldcountries.app.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import timber.log.Timber


class WCApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initDagger(this)
    }

    fun initDagger(application: Application) {
        component = DaggerAppComponent.builder()
            .application(application)
            .build()
        component.inject(this)
    }
}