package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetDataInRecyclerViewActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data_in_recycler_view2)

        val recyclerView: RecyclerView = findViewById(R.id.RecyclerView_All_Data)
        val userList: ArrayList<DataModel> = ArrayList()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
            .getEmployees()

        retrofit.enqueue(object : Callback<UserDataModel> {
            override fun onResponse(
                call: Call<UserDataModel>,
                response: Response<UserDataModel>
            ) {
                if (response.isSuccessful) {
                    Log.e("Success", response.body().toString())
                    val responseData = response.body()
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@GetDataInRecyclerViewActivity2)
                        recyclerView.adapter = MyAdapter(responseData?.list ?: mutableListOf())
                    }
                    Toast.makeText(
                        this@GetDataInRecyclerViewActivity2,
                        responseData?.subject ?: "",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UserDataModel>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

        })


        val button: Button = findViewById(R.id.Button)

        button.setOnClickListener {

        }
    }


}
