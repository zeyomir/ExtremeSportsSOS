package io.github.zeyomir.extremesportssos.view.contact

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.zeyomir.extremesportssos.MockApplication
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.di.component.DaggerTestComponent
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.view.message.configMessage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ConfigureContactActivityTest {

    @Rule
    @JvmField
    var activityRule: IntentsTestRule<ConfigureContactActivity> = IntentsTestRule(ConfigureContactActivity::class.java, true, false)

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
    fun emptyIfNothingSaved() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        activityRule.launchActivity(null)

        configContact {
            checkNumber("")
            checkName("")
        }
    }

    @Test
    fun readsSavedState() {
        whenever(keyValueService.getSosContact()).thenReturn(SosContact("666 666 666", "Evil"))
        activityRule.launchActivity(null)

        configContact {
            checkNumber("666 666 666")
            checkName("Evil")
        }
    }

    @Test
    fun picksFromContacts() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        activityRule.launchActivity(null)

        configContact {
            pickFromContacts()
        }
    }

    @Test
    fun numberIsRequired() {
        whenever(keyValueService.getSosContact()).thenReturn(null)

        configContact {
            goToNextScreen()
            checkMessageWithValidationErrors(activityRule.activity)
        }
    }

    @Test
    fun savesData() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        activityRule.launchActivity(null)

        configContact {
            enterNumber("123")
            enterName("Joe")
            goToNextScreen()
        }

        verify(keyValueService).saveSosContact(eq(SosContact("123", "Joe")))
    }

    @Test
    fun canGoToNextScreenIfNumberIsProvided() {
        whenever(keyValueService.getSosContact()).thenReturn(null)
        activityRule.launchActivity(null)

        configContact {
            enterNumber("123")
            goToNextScreen()
        }
        configMessage {}
    }
}
