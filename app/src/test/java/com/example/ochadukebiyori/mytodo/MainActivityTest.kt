package com.example.ochadukebiyori.mytodo

import android.app.Activity
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import org.junit.After
import org.junit.Before
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.google.common.truth.Truth.assertThat

import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra

import org.hamcrest.Matchers.*

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    lateinit var scenario: ActivityScenario<MainActivity>

    lateinit var listView: ListView

    @Before
    fun setUp() {
        scenario = activityScenarioRule.scenario
        scenario.onActivity { activity ->
            listView = activity.findViewById(R.id.todoList)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun 追加ボタンを押すとタスクが追加されるべき() {
        assertThat(listView.count).isEqualTo(2)

        onView(withId(R.id.addButton)).perform(click())

        assertThat(listView.count).isEqualTo(3)
    }

    @Test
    fun リストのアイテムを選択すると詳細データを渡すべき() {
        // タスクを選択する
        onData(allOf(`is`(instanceOf(String::class.java))))
            .inAdapterView(withId(R.id.todoList))
            .atPosition(1)
            .perform(click())

        intended(allOf(
            hasComponent(EditItemActivity::class.java.name),
            hasExtra("title", "todo2")))
    }

    @Test
    fun タスクの詳細で保存ボタンを押すと保存されるべき() {
        // タスクを選択する
        onData(allOf(`is`(instanceOf(String::class.java))))
            .inAdapterView(withId(R.id.todoList))
            .atPosition(1)
            .perform(click())

//        val editItemActivity
        onView(withId(R.id.itemTitleText)).check(matches(withText("todo2")))
//        onData(allOf(`is`(instanceOf(Map.class)), hasEntry(equalTo("todo1"))))).perform(click())
        // タスクの詳細画面で編集する
        // タスクの詳細画面にある保存ボタンを押す
        // 編集した内容が保存されている
//        onView(withId(R.id.addButton)).perform(click())
//
//        assertThat(listView.count).isEqualTo(3)
    }

    @Test
    fun タスクの詳細で削除ボタンを押すと削除されるべき() {}
}