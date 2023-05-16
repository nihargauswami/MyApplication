package com.example.myapplication


import com.example.myapplication.model.AddUserResponse
import com.example.myapplication.model.Data
import com.example.myapplication.model.PostData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {

    @GET("1")
    fun getData(): Call<MyData>

    @GET("employees")
    fun getEmployees(): Call<UserDataModel>

    @GET("newUsers/signupRequireData")
    fun getCountyCode(): Call<Data>

    @POST("newUsers/v1/addTrialUser")
    fun posting(
        @Body postItem: PostData
    ) : Call<AddUserResponse>

}