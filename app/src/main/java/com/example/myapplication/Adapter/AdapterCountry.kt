package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Countries
import com.example.myapplication.R

class AdapterCountry(
    private var countryList: MutableList<Countries>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AdapterCountry.CountryViewHolder>() {

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val country: TextView = itemView.findViewById(R.id.Counrty_Craete_Account)
        var id: Int = -1

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setFilterList(newCountryList : MutableList<Countries>){
        countryList = newCountryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.select_country_create_account, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val itemList = countryList[position]
        holder.country.text = itemList.name
        holder.id = itemList.id
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position, itemList.name, itemList.id)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {
        fun onClick(position: Int, country: String, id: Int)
    }


}