package com.example.myapplication

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyIntercepter())
    }.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummy.restapiexample.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    fun <T> buildService(service : Class<T>) : T{
        return retrofit.create(service)
    }

}