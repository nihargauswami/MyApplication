package com.example.myapplication


import com.example.myapplication.Model.AddUserResponse
import com.example.myapplication.Model.Data
import com.example.myapplication.Model.PostData
import retrofit2.Call
import retrofit2.Response
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