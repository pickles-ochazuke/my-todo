package com.example.ochadukebiyori.mytodo

// android
import android.app.Activity
import android.app.Application
import android.app.Instrumentation
import android.content.ComponentName
import android.content.Intent
import android.widget.ListView

// androidx.test
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

// JUnit
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

// Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra

// hamcrest
import org.hamcrest.Matchers.*

import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
//    @get:Rule
//    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    lateinit var listView: ListView

    @Before
    fun setUp() {
        intentsTestRule.activity.apply {
            listView = this.findViewById(R.id.todoList)
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
    fun Deleteのインテントを受け取ると対象のアイテムを削除するべき() {

        // Delete のインテントを作成する
        val resultData = Intent()
        resultData.putExtra("position", 1)
        resultData.putExtra("event", "delete")

        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
        val componentName = ComponentName(
                ApplicationProvider.getApplicationContext<Application>(),
                EditItemActivity::class.java
            )
        intending(hasComponent(componentName)).respondWith(result)

//        val editScenario = ActivityScenario.launch(EditItemActivity::class.java)
//        editScenario.onActivity {
//            it.findViewById<Button>(R.id.deleteButton).performClick()
//        }
//        editScenario.close()

        // タスクを選択する
//        val mainScenario = ActivityScenario.launch(MainActivity::class.java)

        onData(allOf(`is`(instanceOf(String::class.java))))
            .inAdapterView(withId(R.id.todoList))
            .atPosition(1)
            .perform(click())

        // 結果を見る
        assertThat(listView.count).isEqualTo(1)
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
}