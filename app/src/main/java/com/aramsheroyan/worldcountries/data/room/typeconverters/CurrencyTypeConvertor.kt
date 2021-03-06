/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.data.room.typeconverters

import androidx.room.TypeConverter
import com.aramsheroyan.worldcountries.data.room.Country.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyTypeConvertor {
    companion object {
        private val gson = Gson()
        val type = object : TypeToken<List<Currency>>() {}.type

        @TypeConverter
        @JvmStatic
        fun fromCurrencyToString(data: List<Currency>?): String {
            if (data == null) {
                return ""
            }

            return gson.toJson(data, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToCurrency(data: String?): List<Currency> {
            if (data == null) {
                return emptyList()
            }

            return gson.fromJson(data, type)
        }

    }
}