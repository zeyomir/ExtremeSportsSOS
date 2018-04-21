package io.github.zeyomir.extremesportssos.view.map

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import io.github.zeyomir.extremesportssos.R


fun map(func: MapActivityRobot.() -> Unit): MapActivityRobot {
    onView(withText(R.string.map_button_help)).check(matches(isDisplayed()))
    return MapActivityRobot().apply {func()}
}

class MapActivityRobot {
}