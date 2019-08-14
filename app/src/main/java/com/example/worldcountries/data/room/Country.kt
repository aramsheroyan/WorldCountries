package com.example.worldcountries.data.room

import androidx.room.*
import io.reactivex.Maybe
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
    @ColumnInfo(name = "languages") val languages: List<Language>,
    @ColumnInfo(name = "flag") val flag: String?
) {

    @Dao
    interface CountriesDAO {

        @Query("SELECT * FROM countries")
        fun getAll(): Single<List<Country>>

        @Query("SELECT * FROM countries where name=:name")
        fun getByName(name: String): Maybe<Country>

        @Insert
        fun insertAll(vararg country: Country)
    }

    data class Currency(
        val code: String,
        val name: String,
        val symbol: String?
    ) {
        override fun toString(): String {
            return if (symbol != null) " $code ($symbol) $name" else " $code $name"
        }
    }

    data class Language(
        val name: String
    ) {
        override fun toString(): String {
            return name
        }
    }

    override fun toString(): String {
        return "Country(uid=$uid, name=$name, alpha2Code=$alpha2Code, alpha3Code=$alpha3Code," +
                "capital=$capital, region=$region, subregion=$subregion, population=$population, " +
                "area=$area, timezones=$timezones, borders=$borders, currencies=$currencies, " +
                "languages=$languages, flag=$flag)"
    }


}

