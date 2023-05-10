package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Countries
import com.example.myapplication.R

class AdapterCountryCode(
    private val countryList: MutableList<Countries>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AdapterCountryCode.CountryViewHolder>() {



    inner class CountryViewHolder(itemview: View, onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemview) {
        val countryCode: TextView = itemview.findViewById(R.id.Country_Code_1)
        val countries: TextView = itemview.findViewById(R.id.Country)

//        init {
//            itemview.setOnClickListener {
//                onItemClickListener.onClick(adapterPosition)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_country_code, parent, false)


        return CountryViewHolder(itemview, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val itemList = countryList[position]
        holder.countries.text = itemList.name
        holder.countryCode.text = itemList.phonecode.toString()
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(itemList.phonecode.toString(), position)
            notifyDataSetChanged()
        }

    }


    interface OnItemClickListener {
        fun onClick(phonecode: String, position: Int)

    }
}