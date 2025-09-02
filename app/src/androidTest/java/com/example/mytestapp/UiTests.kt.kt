package com.example.mytestapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.ViewAction
import androidx.test.espresso.UiController
import android.view.View

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class UiTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(60, TimeUnit.SECONDS)

        onView(isRoot()).perform(object : ViewAction {
            override fun getConstraints() = isRoot()
            override fun getDescription() = "Send key events to unlock screen"
            override fun perform(uiController: UiController, view: View?) {
                uiController.injectKeyEvent(android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_MENU))
                uiController.loopMainThreadForAtLeast(1000)
            }
        })
    }

//    private fun waitFor(millis: Long) = object : ViewAction {
//        override fun getConstraints() = isRoot()
//        override fun getDescription() = "Wait for $millis milliseconds."
//        override fun perform(uiController: UiController, view: View?) {
//            uiController.loopMainThreadForAtLeast(millis)
//        }
//    }

    @Test
    fun testInputAndButtonClick() {
        // Ждем фокус окна
//        onView(isRoot()).perform(waitFor(10000))


        // Вводим текст
        onView(withId(R.id.name_input)).perform(typeText("Hovsep"), closeSoftKeyboard())
        onView(withId(R.id.surename_input)).perform(typeText("Avagyan"), closeSoftKeyboard())

        // Кликаем кнопку
        onView(withId(R.id.click_button)).perform(click())

        // Проверяем приветствие!!
        onView(withId(R.id.greeting_output))
            .check(matches(withText("Hello Hovsep Avagyan!")))
    }

    @Test
    fun testButtonClickCounter() {
        // Нажимаем кнопку 3 раза
        repeat(3) {
            onView(withId(R.id.click_button)).perform(click())
        }

        onView(withId(R.id.clicked_output))
            .check(matches(withText("Button clicked 3 times")))
    }

    @Test
    fun testEmptyInputShowsGuest() {
        // По умолчанию поля пустые, проверяем текст
        onView(withId(R.id.greeting_output))
            .check(matches(withText("Hello Guest!")))
    }
}