package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Data
import com.example.myapplication.Model.Industry
import com.example.myapplication.R

class AdapterSelectIndustry(
    private val industryList: MutableList<Industry>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AdapterSelectIndustry.IndustryViewHolder>() {


    inner class IndustryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val industry: TextView = itemView.findViewById(R.id.Counrty_Craete_Account)
        var id: Int = -1


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSelectIndustry.IndustryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_country, parent, false)
        return IndustryViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdapterSelectIndustry.IndustryViewHolder, position: Int) {
        val itemView = industryList[position]
        holder.industry.text = itemView.name
        holder.id = itemView.id
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position, itemView.name, itemView.id)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return industryList.size
    }

    interface OnItemClickListener {
        fun onClick(position: Int, industry: String, id: Int)
    }
}