package com.example.ochadukebiyori.mytodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    val myStringArray = arrayListOf<String>("todo1", "todo2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray)

        val listView: ListView = findViewById(R.id.todoList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            // Do something in response to the click
            Toast.makeText(this, "TextView: ${(view as TextView)?.text}, position: $position, id: $id", Toast.LENGTH_SHORT).show()

            // 編集画面を表示する
            val editIntent = Intent(this, EditItemActivity::class.java).apply {
                this.putExtra("title", view.text)
            }
            startActivity(editIntent)
        }

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            adapter.add("todo${adapter.count+1}")
        }
    }
}
