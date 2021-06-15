package com.example.demomvvm.data.source.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    fun <T> generate(
        baseUrl: String,
        serviceClass: Class<T>,
        gson: Gson
    ): T {
        val okHttpClient = provideHttpClient()
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return builder.create(serviceClass)
    }

    private fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuild = OkHttpClient.Builder().apply {
            connectTimeout(3000, TimeUnit.SECONDS)
            readTimeout(3000, TimeUnit.SECONDS)
        }
        return okHttpClientBuild.build()
    }
}
