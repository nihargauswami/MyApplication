package com.example.myapplication


import com.example.myapplication.Model.AllData
import com.example.myapplication.Model.Countries
import com.example.myapplication.Model.Data
import com.example.myapplication.Model.Expertise
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RetrofitAPI {

    @GET("1")
    fun getData(): Call<MyData>

    @GET("employees")
    fun getEmployees(): Call<UserDataModel>


//    @Headers(
//        "device-name:ONEPLUS A6000",
//        "device-os:Android",
//        "device-os-version:11",
//        "app-version: 0.2.26",
//        "timezone:Asia/Kolkata",
//        "device-token : f9SJ0-HGQYiDwWBW-9pTIs:APA91bFgCG6EP20gjnro0w6_JeRI-GGCioOemqNK_Po-dCpmd2rZBnJ0IenVHi0eXyFnq2upfM48oVjE0yHBIUgt_njBQSKUM32sQNTuyHaNNt3NHCNDfaztyGNq8CB4xC_qi1BTh5Fu"
//    )
    @GET("newUsers/signupRequireData")
    fun getCountyCode(): Call<Data>

}