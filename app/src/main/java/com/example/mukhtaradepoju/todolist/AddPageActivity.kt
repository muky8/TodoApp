package com.example.mukhtaradepoju.todolist

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.mukhtaradepoju.todolist.DataClass.TodoData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_page.*

class AddPageActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var mDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)


        database = FirebaseDatabase.getInstance().reference
        addButton?.setOnClickListener {
            writeNewData("user1234", editText.text.toString())
            editText.setText("")
            finish()
        }


    }

    private fun writeNewData(userId: String, itemlist: String) {
        val dataList = TodoData(userId, itemlist)
        database.child(userId).child("data").setValue(dataList)
    }
}
