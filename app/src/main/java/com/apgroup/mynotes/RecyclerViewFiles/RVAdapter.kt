package com.apgroup.mynotes.RecyclerViewFiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apgroup.mynotes.R
import kotlinx.android.synthetic.main.notes_home_page.view.*

class RVAdapter(val context:Context, val notesList:ArrayList<RVDataClass>,private val rvAllClicks: RVAllClicks):RecyclerView.Adapter<RVAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notes_home_page,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder!!.BindItems(notesList[position])
    }


    inner class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

            val title = itemView.findViewById<TextView>(R.id.notes_title)
            val body = itemView.findViewById<TextView>(R.id.notes_body)


        fun BindItems(notesList:RVDataClass){

            title.text = notesList.title
            body.text = notesList.notes

            itemView.setOnClickListener {
                rvAllClicks.onRVClick(notesList)
            }

            itemView.deleteBtn.setOnClickListener {
                rvAllClicks.onItemClick(notesList.id)
            }

        }


    }
}