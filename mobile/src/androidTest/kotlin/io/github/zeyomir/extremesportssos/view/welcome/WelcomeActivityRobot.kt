package io.github.zeyomir.extremesportssos.view.welcome

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import io.github.zeyomir.extremesportssos.R


fun welcome(func: WelcomeActivityRobot.() -> Unit): WelcomeActivityRobot {
    onView(withText(R.string.welcome_activity_text)).check(matches(isDisplayed()))
    return WelcomeActivityRobot().apply { func() }
}

class WelcomeActivityRobot {
    fun goToConfig() {
        onView(withText(R.string.welcome_activity_config_button)).perform(click())
    }
}