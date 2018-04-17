package io.github.zeyomir.extremesportssos.view.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import io.github.zeyomir.extremesportssos.R
import org.hamcrest.CoreMatchers.containsString

fun main(func: MainActivityRobot.() -> Unit): MainActivityRobot {
    onView(withText(R.string.main_text)).check(matches(isDisplayed()))
    return MainActivityRobot().apply {func()}
}

class MainActivityRobot {
    fun verifyContact(string: String) {
        onView(withId(R.id.contact_info)).check(matches(withText(containsString(string))))
    }

    fun verifyMessage(string: String) {
        onView(withId(R.id.sos_message)).check(matches(withText(string)))
    }

    fun goToConfig() {
        onView(withText(R.string.main_settings)).perform(click())
    }

    fun start() {
        onView(withText(R.string.main_start)).perform(click())
    }
}