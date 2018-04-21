package io.github.zeyomir.extremesportssos.view.welcome

import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import io.github.zeyomir.extremesportssos.MockApplication
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.di.component.DaggerTestComponent
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.view.contact.configContact
import io.github.zeyomir.extremesportssos.view.main.main
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class WelcomeActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<WelcomeActivity> = ActivityTestRule(WelcomeActivity::class.java, true, false)

    private lateinit var keyValueService: KeyValueService

    @Before
    fun setup() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MockApplication
        val testComponent = DaggerTestComponent.builder().application(app).build()
        testComponent.inject(app)

        keyValueService = testComponent.keyValueService()
    }

    @Test
    fun goesToNextScreenWhenConfigured() {
        whenever(keyValueService.getSosContact()).thenReturn(SosContact("123456789", null))
        whenever(keyValueService.getSosMessage()).thenReturn("foo")
        activityRule.launchActivity(null)

        main {}
    }

    @Test
    fun canGoToConfigWhenNeeded() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        whenever(keyValueService.getSosMessage()).thenReturn(null)
        activityRule.launchActivity(null)

        welcome {
            goToConfig()
        }
        configContact {}
    }

}
