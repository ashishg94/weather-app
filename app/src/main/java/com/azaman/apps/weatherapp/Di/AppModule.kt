package com.azaman.apps.weatherapp.Di

import com.azaman.apps.weatherapp.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(client: OkHttpClient):Retrofit=
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    fun providesApiService(retrofit: Retrofit):ApiService=
        retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesOkHtpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}

