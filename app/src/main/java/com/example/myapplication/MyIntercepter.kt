package com.example.myapplication

import okhttp3.Interceptor
import okhttp3.Response

class MyIntercepter :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()
           .newBuilder()
           .addHeader("device-name","ONEPLUS A6000")
           .addHeader("device-os","Android")
           .addHeader("device-os-version","11")
           .addHeader("app-version","0.2.26")
           .addHeader("timezone","Asia/Kolkata")
           .addHeader("device-token","f9SJ0-HGQYiDwWBW-9pTIs:APA91bFgCG6EP20gjnro0w6_JeRI-GGCioOemqNK_Po-dCpmd2rZBnJ0IenVHi0eXyFnq2upfM48oVjE0yHBIUgt_njBQSKUM32sQNTuyHaNNt3NHCNDfaztyGNq8CB4xC_qi1BTh5Fu")
           .build()
    return chain.proceed(request)

    }
}