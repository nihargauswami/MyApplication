package com.example.myapplication.fragment

import android.annotation.SuppressLint
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
import com.example.myapplication.adapter.AdapterSelectExperties
import com.example.myapplication.model.Data
import com.example.myapplication.model.Expertise
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


class SelectExpertiseFragment : Fragment(), AdapterSelectExperties.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var expList: MutableList<Expertise>
    private var adapter: AdapterSelectExperties? = null

    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_expertise, container, false)
        searchView = view.findViewById(R.id.Search_Country_1_2)
        recyclerView = view.findViewById(R.id.Recycler_View_Country_2)
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
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter?.notifyDataSetChanged()
                        recyclerView.adapter =
                            AdapterSelectExperties(
                                response.body()?.data?.expertise!!,
                                this@SelectExpertiseFragment
                            )
                    }
                    expList = response.body()?.data?.expertise!!
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
            val filterList = ArrayList<Expertise>()
            for (i in expList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {
                Toast.makeText(activity, "No data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter = AdapterSelectExperties(filterList, this)
                adapter!!.setFilterList(filterList)
                recyclerView.adapter = adapter
            }
        }
    }

    private fun goToPreviousScreen(userInput: String, id: Int, expList: ArrayList<String>) {
        /* setFragmentResult(
             "2",
             bundleOf("experties" to userInput)
         )*/
        setFragmentResult(
            "6",
            bundleOf("id" to id)
        )
        setFragmentResult(
            "9",
            bundleOf("expList" to expList)
        )

    }

    override fun onCLick(position: Int, expertise: String, id: Int, expList: ArrayList<String>) {
        goToPreviousScreen(expertise, id, expList)
        findNavController().navigateUp()


    }


}