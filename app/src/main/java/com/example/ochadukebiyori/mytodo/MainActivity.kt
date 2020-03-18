package com.example.ochadukebiyori.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.todo_list_item.view.*

class MainActivity : AppCompatActivity() {

    val myStringArray = arrayListOf<String>("Test1", "Test2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray)

        val listView: ListView = findViewById(R.id.todoList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // Do something in response to the click
            Toast.makeText(this, "TextView: ${(view as TextView)?.text}, position: $position, id: $id", Toast.LENGTH_SHORT).show();
        }
    }
}
