package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList: MutableList<DataModel>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.Name)
        val salary : TextView = itemView.findViewById(R.id.Salary)
        val id : TextView = itemView.findViewById(R.id.Id)
        val age : TextView = itemView.findViewById(R.id.Age)

        fun bindView(userDataModel : DataModel){
            name.text = userDataModel.employee_name
            salary.inputType = userDataModel.employee_salary
            id.inputType = userDataModel.id
            age.inputType = userDataModel.employee_age
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.user_data,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            return holder.bindView(userList[position])
    }


}