package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Industry
import com.example.myapplication.R

class AdapterSelectIndustry(
    private var industryList: MutableList<Industry>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AdapterSelectIndustry.IndustryViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(newIndustryList: MutableList<Industry>) {
        industryList = newIndustryList
        notifyDataSetChanged()
    }

    private val indList = ArrayList<String>()


    inner class IndustryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val industry: TextView = itemView.findViewById(R.id.Select_Ind_Craete_Account)
        var id: Int = -1
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSelectIndustry.IndustryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_ind_exp, parent, false)
        return IndustryViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdapterSelectIndustry.IndustryViewHolder, position: Int) {
        val itemView = industryList[position]
        holder.industry.text = itemView.name
        holder.id = itemView.id
        holder.checkBox.isChecked = itemView.isSelecterd
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position, itemView.name, itemView.id, indList)
            notifyDataSetChanged()
        }
        holder.checkBox.setOnClickListener{
            if (industryList != null && industryList.size > 0) {
                holder.checkBox.text = itemView.name
                if (holder.checkBox.isChecked) {
                    indList.add(industryList[position].toString())
                }else{
                    indList.remove(industryList.toString())
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return industryList.size
    }

    interface OnItemClickListener {
        fun onClick(position: Int, industry: String, id: Int, indList : ArrayList<String>)
    }
}