package com.example.masterytechtask.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masterytechtask.R
import com.example.masterytechtask.model.Driver

class DriverListAdapter (var items: List<Driver>, var listener: OnItemClickListener) :
    RecyclerView.Adapter<DriverListAdapter.DriverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        return DriverViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val current = items[position]
        holder.bind(current)
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val firstNameTxt: TextView = itemView.findViewById(R.id.firstNameTxt)
        private val lastNameTxt: TextView = itemView.findViewById(R.id.lastNameTxt)

        fun bind(item: Driver) {
            firstNameTxt.text = item.firstName
            lastNameTxt.text = item.lastName
        }

        companion object {
            fun create(parent: ViewGroup): DriverViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_driver, parent, false)
                return DriverViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}