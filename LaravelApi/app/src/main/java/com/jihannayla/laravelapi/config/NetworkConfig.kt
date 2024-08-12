package com.jihannayla.laravelapi.config

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkConfig {
    val BASE_URL:String = "http://10.0.2.2:8000/api/"

    private fun setOkhttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        ).setLevel(HttpLoggingInterceptor.Level.BODY)

        return  OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .callTimeout(15L, TimeUnit.SECONDS)
            .build()
    }

    private fun setRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(setOkhttp())
            .build()
    }

    fun getServices(): ApiServices {
        return setRetrofit().create(ApiServices::class.java)

    }
}