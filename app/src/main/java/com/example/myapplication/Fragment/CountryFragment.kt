package com.example.myapplication.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.AdapterCountry
import com.example.myapplication.Model.Data
import com.example.myapplication.MyIntercepter
import com.example.myapplication.R
import com.example.myapplication.RetrofitAPI
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryFragment : Fragment(), AdapterCountry.OnItemClickListener {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country, container, false)

        recyclerView = view.findViewById(R.id.Recycler_View_Country_1)

        val client = OkHttpClient.Builder().apply {
            addInterceptor(MyIntercepter())
        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-15-206-100-11.ap-south-1.compute.amazonaws.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
            .getCountyCode()

        retrofit.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data?.countries!!
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = AdapterCountry(responseBody, this@CountryFragment)
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })


        return view
    }

    private fun gotoPreviousScreen(userInput: String) {
        setFragmentResult(
            "1",
            bundleOf("country" to userInput)
        )
    }

    override fun onClick(position: Int, country: String) {

        gotoPreviousScreen(country)
        findNavController().navigateUp()

    }


}