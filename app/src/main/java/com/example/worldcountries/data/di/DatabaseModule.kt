package com.example.worldcountries.data.di

import android.app.Application
import androidx.room.Room
import com.example.worldcountries.data.room.Country
import com.example.worldcountries.data.room.WCRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): WCRoomDatabase {
        return Room.databaseBuilder(application.applicationContext, WCRoomDatabase::class.java, "wc-database")
            .build()
    }

    @Provides
    fun provideCountriesDAO(wcRoomDatabase: WCRoomDatabase): Country.CountriesDAO {
        return wcRoomDatabase.countriesDAO()
    }
}
