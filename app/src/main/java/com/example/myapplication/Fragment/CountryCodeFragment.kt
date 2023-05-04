package com.example.myapplication.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.AdapterCountryCode
import com.example.myapplication.LoginFragment
import com.example.myapplication.Model.Countries
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

class CountryCodeFragment : Fragment(), AdapterCountryCode.OnItemClickListener {
    //    lateinit var searchView: SearchView
    lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_code, container, false)

//        searchView = view.findViewById(R.id.Search_Country)
        recyclerView = view.findViewById(R.id.Recycler_View_Country)


        val client = OkHttpClient.Builder().apply {
            addInterceptor(MyIntercepter())
        }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-15-206-100-11.ap-south-1.compute.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitAPI::class.java)
            .getCountyCode()

        retrofit.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data?.countries
                    val adapter = AdapterCountryCode(responseBody!!, this@CountryCodeFragment)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter =
                            AdapterCountryCode(responseBody, this@CountryCodeFragment)
                        recyclerView.adapter = adapter

                    }
                }

            }


            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

        })


//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filterList()
//                return true
//            }

//            private fun filterList(query: String?) {
//if (query != null){
//val filterList = ArrayList<Countries>()
//for (i in retrofit.)
//}
//
//            }
//
//        })
//


        return view
    }

    private fun gotoPreviousScreen(userInput: String) {
        setFragmentResult(
            "1",
            bundleOf("phonecode" to userInput)
        )
        setFragmentResult(
            "4",
            bundleOf("phonecode" to userInput)
        )
    }


    override fun onClick(phonecode: String, position: Int) {
        Log.d("seccus", phonecode)
        gotoPreviousScreen(phonecode)
        findNavController().navigateUp()

    }


}