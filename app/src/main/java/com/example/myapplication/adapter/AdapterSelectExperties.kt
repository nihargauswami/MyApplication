package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.Expertise
import com.example.myapplication.R

class AdapterSelectExperties(
    private var expertiesList: MutableList<Expertise>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AdapterSelectExperties.ExpertiesViewHolder>() {

    private var expList = ArrayList<String>()


    @SuppressLint("NotifyDataSetChanged")
    fun setFilterList(newExpertiesList: MutableList<Expertise>) {
        expertiesList = newExpertiesList
        notifyDataSetChanged()
    }

    inner class ExpertiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        val expText: TextView = itemView.findViewById(R.id.Select_Ind_Craete_Account)
        var id: Int = -1
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpertiesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_ind_exp, parent, false)
        return ExpertiesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return expertiesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ExpertiesViewHolder, position: Int) {
        val itemView = expertiesList[position]
//        holder.expText.text = itemView.name
        holder.checkBox.text = itemView.name
        holder.id = itemView.id
        holder.itemView.setOnClickListener {
            onItemClickListener.onCLick(position, itemView.name, itemView.id , expList)
            notifyDataSetChanged()
        }
        holder.checkBox.setOnClickListener {
            if(expertiesList.size > 0){
                if (holder.checkBox.isChecked){
                    expList.add(expertiesList[position].name)
                }else{
                    expList.remove(expertiesList.toString())
                }

            }
        }
    }

    interface OnItemClickListener {
        fun onCLick(position: Int, expertise: String, id: Int , expList : ArrayList<String>)
    }

}