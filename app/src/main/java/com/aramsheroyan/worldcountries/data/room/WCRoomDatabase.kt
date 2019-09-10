package com.aramsheroyan.worldcountries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aramsheroyan.worldcountries.data.room.typeconverters.CurrencyTypeConvertor
import com.aramsheroyan.worldcountries.data.room.typeconverters.LanguageTypeConverter
import com.aramsheroyan.worldcountries.data.room.typeconverters.StringListConverter

@Database(entities = arrayOf(Country::class,ProgramCountry::class), version = 1)
@TypeConverters(
    StringListConverter::class,
    CurrencyTypeConvertor::class,
    LanguageTypeConverter::class
)
abstract class WCRoomDatabase : RoomDatabase() {
    abstract fun countriesDAO(): Country.CountriesDAO
    abstract fun capitalsProgramDAO(): ProgramCountry.CapitalsProgramDAO

}