package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Countries
import com.example.myapplication.R

class AdapterCountryCode(
    private var countryList: MutableList<Countries>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AdapterCountryCode.CountryViewHolder>(){
//    var countryFilterList = countryList

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(newCountryList: MutableList<Countries>) {
        countryList = newCountryList
        notifyDataSetChanged()
    }


    inner class CountryViewHolder(itemview: View) :
        RecyclerView.ViewHolder(itemview) {
        val countryCode: TextView = itemview.findViewById(R.id.Country_Code_1)
        val countries: TextView = itemview.findViewById(R.id.Country)
        var id: Int = -1


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemview: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_country_code, parent, false)


        return CountryViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val itemList = countryList[position]
        holder.countries.text = itemList.name
        holder.id = itemList.id
        holder.countryCode.text = itemList.phonecode.toString()
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(itemList.phonecode.toString(), position, itemList.id)
            notifyDataSetChanged()
        }

    }


    interface OnItemClickListener {
        fun onClick(phonecode: String, position: Int, id: Int)

    }
}

    /*override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList as ArrayList<Countries>
                } else {
                    val resultList = ArrayList<Countries>()
                    for (row in countryList) {
                        if (row.name.lowercase(Locale.ROOT).contains(
                                constraint.toString()
                                    .lowercase(Locale.ROOT)
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Countries>
                notifyDataSetChanged()
            }
        }
    }
}*/

