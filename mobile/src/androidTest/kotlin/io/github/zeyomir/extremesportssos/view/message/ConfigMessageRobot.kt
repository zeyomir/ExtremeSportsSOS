package io.github.zeyomir.extremesportssos.view.message

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import io.github.zeyomir.extremesportssos.R


fun configMessage(func: ConfigMessageRobot.() -> Unit): ConfigMessageRobot {
    Espresso.onView(ViewMatchers.withText(R.string.configure_message_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    return ConfigMessageRobot().apply { func() }
}

class ConfigMessageRobot {
}
