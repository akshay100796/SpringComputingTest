package com.example.springtak.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.springtak.databinding.LayoutRecyclerEmployeeBinding
import com.example.springtak.utils.room.Table

class EmployeeAdapter(private val list : List<Table>): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(val binding: LayoutRecyclerEmployeeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(LayoutRecyclerEmployeeBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        val emp = list[position]
        holder.binding.apply {
            idFname.text = emp.fname
            idLname.text = emp.lname
            idAge.text = "Age: ${emp.age}"
            idAddress.text = emp.address
            idCity.text = emp.city
        }
    }
}