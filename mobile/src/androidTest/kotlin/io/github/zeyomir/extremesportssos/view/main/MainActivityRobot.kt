package io.github.zeyomir.extremesportssos.view.main

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import io.github.zeyomir.extremesportssos.R

fun main(func: MainActivityRobot.() -> Unit): MainActivityRobot {
    onView(withText(R.string.main_start)).check(matches(isDisplayed()))
    return MainActivityRobot().apply {func()}
}

class MainActivityRobot {
}