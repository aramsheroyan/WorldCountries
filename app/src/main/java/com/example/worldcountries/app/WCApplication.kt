package com.example.worldcountries.app

import android.app.Application
import com.facebook.stetho.Stetho

class WCApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}