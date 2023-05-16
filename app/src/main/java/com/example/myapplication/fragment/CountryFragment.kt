package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.AdapterCountry
import com.example.myapplication.model.Countries
import com.example.myapplication.model.Data
import com.example.myapplication.MyIntercepter
import com.example.myapplication.R
import com.example.myapplication.RetrofitAPI
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class CountryFragment : Fragment(), AdapterCountry.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var countyList : MutableList<Countries>
    private var adapter : AdapterCountry ?= null

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
                    countyList = responseBody
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })

        searchView = view.findViewById(R.id.Search_Country_1)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)

                return true
            }

        })

        return view
    }

    private fun filterList(query : String?) {

        if(query != null){
            val filterList = ArrayList<Countries>()
            for (i in countyList){
                if (i.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))){
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()){
                Toast.makeText(activity,"No data Found",Toast.LENGTH_SHORT).show()
            }else{
                adapter = AdapterCountry(countyList,this)
                adapter!!.setFilterList(filterList)
                recyclerView.adapter = adapter
            }
        }

    }

    private fun gotoPreviousScreen(userInput: String, id: Int) {
        setFragmentResult(
            "1",
            bundleOf("country" to userInput)
        )
        setFragmentResult("5",
            bundleOf("id" to id)
        )
    }

    override fun onClick(position: Int, country: String, id: Int) {

        gotoPreviousScreen(country, id)
        findNavController().navigateUp()

    }


}