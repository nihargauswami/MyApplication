package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

//title = findViewById(R.id.textView)
//        button = findViewById(R.id.Button_Get_Data)
//
//        button.setOnClickListener {
//            val i  = Intent(this@MainActivity , GetDataInRecyclerViewActivity2::class.java )
//            startActivity(i)
//        }
////        getData()
//    }
//
//    private fun getData() {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/todos/")
//
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(RetrofitAPI::class.java)
//
//        val retrofitData = retrofit.getData()
//        retrofitData.enqueue(object : Callback<MyData> {
//            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
//                if (response.body() != null) {
//                    val responseBody = response.body()
//
//                    title.text =  responseBody?.title ?: ""
//                }
//            }
//
//
//            override fun onFailure(call: Call<MyData>, t: Throwable) {
//                Log.d("MainActivity", "onFailure:" + t.message)
//            }
//
//        })