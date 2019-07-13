package com.example.mukhtaradepoju.todolist.Adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mukhtaradepoju.todolist.R
import kotlinx.android.synthetic.main.list_item.view.*

class TodoAdapter(private var dataList:ArrayList<String>,private var context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<TodoAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.tvTodoType.text = dataList.get(position)

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dataList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var tvTodoType: TextView =itemView!!.findViewById(R.id.tv_todo)


    }


    fun removeAt(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }


}