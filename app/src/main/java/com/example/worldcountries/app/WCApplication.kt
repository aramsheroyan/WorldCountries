package com.example.worldcountries.app

import android.app.Application
import com.example.worldcountries.app.di.AppComponent
import com.example.worldcountries.app.di.DaggerAppComponent
import com.facebook.stetho.Stetho


class WCApplication: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initDagger(this)
    }

    fun initDagger(application: Application) {
        component = DaggerAppComponent.builder()
            .application(application)
            .build()
        component.inject(this)
    }
}