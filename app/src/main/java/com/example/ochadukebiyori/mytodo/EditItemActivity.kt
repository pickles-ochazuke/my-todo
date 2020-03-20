package com.example.ochadukebiyori.mytodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        // アイテムタイトルの設定
        val title = intent.getStringExtra("title") ?: ""

        val titleText = findViewById<TextView>(R.id.itemTitleText)
        titleText.text = title

        // 保存ボタンの設定
        val savedButton = findViewById<Button>(R.id.savedButton)
        savedButton.setOnClickListener {

            val result = Intent()

            result.putExtra("title", titleText.text.toString())
            result.putExtra("position", this.intent.getIntExtra("position", -1))

            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}
