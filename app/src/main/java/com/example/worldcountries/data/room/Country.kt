package com.example.worldcountries.data.room

import androidx.room.*
import io.reactivex.Single

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "alpha2Code") val alpha2Code: String?,
    @ColumnInfo(name = "alpha3Code") val alpha3Code: String?,
    @ColumnInfo(name = "capital") val capital: String?,
    @ColumnInfo(name = "region") val region: String?,
    @ColumnInfo(name = "subregion") val subregion: String?,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "area") val area: Float,
    @ColumnInfo(name = "timezones") val timezones: List<String>?,
    @ColumnInfo(name = "borders") val borders: List<String>?,
    @ColumnInfo(name = "currencies") val currencies: List<Currency>,
    @ColumnInfo(name = "languages")val languages: List<Language>,
    @ColumnInfo(name = "flag") val flag: String?
) {

    @Dao
    interface CountriesDAO {

        @Query("SELECT * FROM countries")
        fun getAll(): Single<List<Country>>

        @Insert
        fun insertAll(vararg country: Country)
    }

    data class Currency(
        val code: String,
        val name: String,
        val symbol: String
    )

    data class Language(
        val name: String
    )
}

