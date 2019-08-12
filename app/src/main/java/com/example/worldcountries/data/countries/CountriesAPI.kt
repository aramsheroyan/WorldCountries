package com.example.worldcountries.data.countries

import com.example.worldcountries.data.room.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesAPI {

    @GET("rest/v2/all")
    fun getAll(): Single<List<Country>>
}