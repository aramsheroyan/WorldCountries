/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.app.di

import android.app.Application
import android.content.Context
import com.aramsheroyan.worldcountries.data.countries.CountriesAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplication(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofit(logging: HttpLoggingInterceptor): Retrofit {
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): CountriesAPI {
        return retrofit.create(CountriesAPI::class.java)
    }
}