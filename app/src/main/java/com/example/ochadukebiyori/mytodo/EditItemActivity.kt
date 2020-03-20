package com.example.ochadukebiyori.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        val title = intent.getStringExtra("title") ?: ""

        val titleText = findViewById<TextView>(R.id.itemTitleText)
        titleText.text = title

    }
}
