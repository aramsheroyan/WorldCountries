/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.app

import android.app.Application
import com.aramsheroyan.worldcountries.BuildConfig
import com.aramsheroyan.worldcountries.app.di.AppComponent
import com.aramsheroyan.worldcountries.app.di.DaggerAppComponent
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