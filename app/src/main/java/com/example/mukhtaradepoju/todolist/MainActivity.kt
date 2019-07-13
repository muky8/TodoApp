package com.example.mukhtaradepoju.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import android.util.Log
import android.widget.LinearLayout
import android.widget.SimpleAdapter
import android.widget.Toast
import com.example.mukhtaradepoju.todolist.Adapter.TodoAdapter
import com.example.mukhtaradepoju.todolist.DataClass.TodoData
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val itemList: ArrayList<String> = ArrayList()
    private lateinit var layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    lateinit var adapter: TodoAdapter
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    var dataList: ArrayList<String> = ArrayList();
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemList.clear()
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_list)
        adapter = TodoAdapter(itemList, this)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        ) as androidx.recyclerview.widget.RecyclerView.LayoutManager?
        recyclerView.adapter = adapter
        database = FirebaseDatabase.getInstance().reference

        Thread(Runnable {
            itemList.clear()
            adapter.notifyDataSetChanged()

            //some method here
        }).start()

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewholder: androidx.recyclerview.widget.RecyclerView.ViewHolder, p1: Int) {
                adapter = recyclerView.adapter as TodoAdapter
                adapter.removeAt(viewholder.adapterPosition)
            }

        }




        database.child("user1234").child("data").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {


            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(TodoData::class.java)
                itemList.add(user!!.items!!)
                Log.i("snapshot", user!!.items); }


        })

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        fab_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPageActivity::class.java)
            startActivity(intent)
        }

    }


}
