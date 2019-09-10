package com.aramsheroyan.worldcountries.data.room.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromListToString(list: List<String>?): String {
            return gson.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToList(data: String?): List<String> {
            if (data == null) {
                return emptyList()
            }

            val listType = object : TypeToken<List<String>>() {

            }.type
            return gson.fromJson(data, listType)
        }

    }
}