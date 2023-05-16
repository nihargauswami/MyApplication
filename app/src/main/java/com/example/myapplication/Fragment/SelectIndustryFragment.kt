package com.example.myapplication.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyIntercepter
import com.example.myapplication.R
import com.example.myapplication.RetrofitAPI
import com.example.myapplication.Adapter.AdapterSelectIndustry
import com.example.myapplication.Model.Data
import com.example.myapplication.Model.Industry
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale


class SelectIndustryFragment : Fragment(), AdapterSelectIndustry.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var adapter: AdapterSelectIndustry? = null
    private lateinit var indList: MutableList<Industry>
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_industry, container, false)
        button = view.findViewById(R.id.Button_Select)
        searchView = view.findViewById(R.id.Search_Industry)
        recyclerView = view.findViewById(R.id.Recycler_View_Select_Industry)
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
                    val responseBody = response.body()?.data?.industry!!
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter =
                            AdapterSelectIndustry(responseBody, this@SelectIndustryFragment)
                    }
                    indList = responseBody
                }

            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun filterList(query: String?) {
        if (query != null) {
            val filterList = ArrayList<Industry>()
            for (i in indList) {
                if (i.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {
                Toast.makeText(activity, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {
                adapter = AdapterSelectIndustry(filterList, this)
                adapter!!.filterList(filterList)
                recyclerView.adapter = adapter
            }
        }

    }


    private fun gotoPreviousScreen(userInput: String, id: Int, indList: ArrayList<String>) {
        /*        "3",
                    bundleOf("industry" to userInput)
                )*/
        setFragmentResult(
            "7",
            bundleOf("id" to id)
        )
        setFragmentResult(
            "8",
            bundleOf("indList" to indList)
        )
    }

    override fun onClick(industry: String, id: Int, indList: ArrayList<String>) {
        Log.d("Success", indList.toString())
        gotoPreviousScreen(industry, id, indList)
        button.setOnClickListener {
            findNavController().navigateUp()
        }

    }


}