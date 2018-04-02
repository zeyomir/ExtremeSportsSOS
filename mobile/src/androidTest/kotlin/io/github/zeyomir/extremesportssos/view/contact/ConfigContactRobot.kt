package io.github.zeyomir.extremesportssos.view.contact


import android.support.test.espresso.Espresso.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import io.github.zeyomir.extremesportssos.R

fun configContact(func: ConfigContactRobot.() -> Unit): ConfigContactRobot {
    onView(withText(R.string.configure_contact_pick)).check(matches(isDisplayed()))
    return ConfigContactRobot().apply { func() }
}

class ConfigContactRobot {
}