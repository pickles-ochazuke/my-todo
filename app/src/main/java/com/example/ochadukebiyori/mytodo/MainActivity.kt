package com.example.ochadukebiyori.mytodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {

    var myStringArray = arrayListOf<String>("todo1", "todo2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            myStringArray = savedInstanceState.getStringArrayList("todos") ?: myStringArray
        }
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
                this.putExtra("position", position)
            }
            startActivityForResult(editIntent, 1)
        }

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            adapter.add("todo${adapter.count+1}")
            Log.d("MainActivity", "last: ${myStringArray.last()}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1
            && resultCode == Activity.RESULT_OK
            && data != null)
        {
            val event = data.getStringExtra("event") ?: ""
            if (event == "edit") {
                val text = data.getStringExtra("title") ?: ""
                val position = data.getIntExtra("position", -1)

                // position が正しく取得できない場合は、処理を終了する
                if (position == -1) {
                    return
                }

                val listView = findViewById<ListView>(R.id.todoList)
                val adapter = listView.adapter as ArrayAdapter<String>
                adapter.remove(adapter.getItem(position))
                adapter.insert(text, position)
            }
            else if (event == "delete") {

                val position = data.getIntExtra("position", -1)

                if (position == -1) {
                    return
                }

                val listView = findViewById<ListView>(R.id.todoList)
                val adapter = listView.adapter as ArrayAdapter<String>
                adapter.remove(adapter.getItem(position))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putStringArrayList("todos", myStringArray)
        }

        super.onSaveInstanceState(outState)
    }
}
