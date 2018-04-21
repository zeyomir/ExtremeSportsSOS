package io.github.zeyomir.extremesportssos.view.main

import android.Manifest
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import io.github.zeyomir.extremesportssos.MockApplication
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.data.sensors.ActivityDetectionService
import io.github.zeyomir.extremesportssos.di.component.DaggerTestComponent
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.view.contact.configContact
import io.github.zeyomir.extremesportssos.view.map.map
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    @Rule
    @JvmField
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)

    private lateinit var keyValueService: KeyValueService
    private lateinit var activityDetectionService: ActivityDetectionService

    private val contactNumber = "123456789"
    private val contactName = "ASDF"
    private val sosMessage = "foo"

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MockApplication
        val testComponent = DaggerTestComponent.builder().application(app).build()
        testComponent.inject(app)

        keyValueService = testComponent.keyValueService()

        whenever(keyValueService.getSosContact()).thenReturn(SosContact(contactNumber, contactName))
        whenever(keyValueService.getSosMessage()).thenReturn(sosMessage)

        activityDetectionService = testComponent.activityDetectionService()

        whenever(activityDetectionService.userMovingUpdates()).thenReturn(Observable.empty())
    }

    @Test
    fun displaysContactAndMessage() {
        activityRule.launchActivity(null)

        main {
            verifyContact(contactNumber)
            verifyContact(contactName)
            verifyMessage(sosMessage)
        }
    }

    @Test
    fun canGoToSettings() {
        activityRule.launchActivity(null)

        main {
            goToConfig()
        }
        configContact {}
    }

    @Test
    fun canStartTheMainPartOfTheApp() {
        activityRule.launchActivity(null)

        main {
            start()
        }
        map {}
    }
}