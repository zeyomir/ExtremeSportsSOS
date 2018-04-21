package io.github.zeyomir.extremesportssos.view.contact


import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasType
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import io.github.zeyomir.extremesportssos.R
import org.hamcrest.CoreMatchers.*


fun configContact(func: ConfigContactRobot.() -> Unit): ConfigContactRobot {
    onView(withText(R.string.configure_contact_pick)).check(matches(isDisplayed()))
    return ConfigContactRobot().apply { func() }
}

class ConfigContactRobot {

    fun checkNumber(number: String) {
        onView(withId(R.id.info)).check(matches(withText(number)))
    }

    fun checkName(name: String) {
        onView(withId(R.id.name)).check(matches(withText(name)))
    }

    fun pickFromContacts() {

        onView(withText(R.string.configure_contact_pick)).perform(click())

        intended(allOf(
                hasType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE),
                hasAction(Intent.ACTION_PICK))
        )
    }

    fun enterNumber(number: String) {
        onView(withId(R.id.info)).perform(typeText(number))
        closeSoftKeyboard()
    }

    fun enterName(name: String) {
        onView(withId(R.id.name)).perform(typeText(name))
        closeSoftKeyboard()
    }

    fun goToNextScreen() {
        onView(withId(R.id.next)).perform(click())
    }

    fun checkMessageWithValidationErrors(activity: Activity) {
        onView(withText(R.string.configure_contact_validation_error)).inRoot(withDecorView(not(`is`(activity.window.decorView)))).check(matches(isDisplayed()))
    }
}
