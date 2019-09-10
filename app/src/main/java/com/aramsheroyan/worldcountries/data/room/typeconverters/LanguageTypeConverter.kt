package com.aramsheroyan.worldcountries.data.room.typeconverters

import androidx.room.TypeConverter
import com.aramsheroyan.worldcountries.data.room.Country.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguageTypeConverter {
    companion object {
        private val gson = Gson()
        val type = object : TypeToken<List<Language>>() {}.type

        @TypeConverter
        @JvmStatic
        fun fromLanguageToString(data: List<Language>?): String {
            if (data == null) {
                return ""
            }

            return gson.toJson(data, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToLanguage(data: String?): List<Language> {
            if (data == null) {
                return emptyList()
            }

            return gson.fromJson(data, type)
        }

    }
}