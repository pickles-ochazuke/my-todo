package com.example.ochadukebiyori.mytodo

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import androidx.test.core.app.launchActivity

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun 追加ボタンを押すとタスクが追加されるべき() {
        val scenaio = launchActivity<MainActivity>()


    }

    @Test
    fun タスクの詳細で保存ボタンを押すと保存されるべき() {}

    @Test
    fun タスクの詳細で削除ボタンを押すと削除されるべき() {}
}