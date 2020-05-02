package com.example.ochadukebiyori.mytodo

import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Lifecycle
import org.junit.After
import org.junit.Before
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun 追加ボタンを押すとタスクが追加されるべき() {
        val scenario = launchActivity<MainActivity>()
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity { activity ->
            val listView = activity.findViewById<ListView>(R.id.todoList)
            assertEquals(2, listView.count)
        }

        Espresso.onView(ViewMatchers.withId(R.id.addButton))
            .perform(ViewActions.click())

        scenario.onActivity { activity ->
            val listView = activity.findViewById<ListView>(R.id.todoList)
            assertEquals(3, listView.count)
        }
    }

    @Test
    fun タスクの詳細で保存ボタンを押すと保存されるべき() {}

    @Test
    fun タスクの詳細で削除ボタンを押すと削除されるべき() {}
}