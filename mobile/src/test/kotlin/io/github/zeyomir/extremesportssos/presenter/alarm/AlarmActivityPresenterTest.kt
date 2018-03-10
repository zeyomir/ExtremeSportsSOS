package io.github.zeyomir.extremesportssos.presenter.alarm

import com.nhaarman.mockito_kotlin.*
import io.github.zeyomir.extremesportssos.domain.usecase.PlaySoundUseCase
import io.github.zeyomir.extremesportssos.view.alarm.AlarmView
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.util.concurrent.TimeUnit

class AlarmActivityPresenterTest {

    private lateinit var alarmActivityPresenter: AlarmActivityPresenter
    private lateinit var view: AlarmView
    private lateinit var playSoundUseCase: PlaySoundUseCase

    @Before
    fun setUp() {
        playSoundUseCase = mock()
        view = mock()
        alarmActivityPresenter = AlarmActivityPresenter(playSoundUseCase, 10)
        alarmActivityPresenter.bind(view)
    }

    @Test
    fun plays_sound_every_second() {
        alarmActivityPresenter.applyState(AlarmStatus(10))

        testScheduler.advanceTimeBy(20, TimeUnit.SECONDS)

        verify(playSoundUseCase, times(11)).execute()
    }


    @Test
    fun updates_view_every_second() {
        alarmActivityPresenter.applyState(AlarmStatus(10))

        testScheduler.advanceTimeBy(20, TimeUnit.SECONDS)

        verify(view, times(11)).updateTimer(any())
    }

    @Test
    fun respects_how_many_seconds_left() {
        alarmActivityPresenter.applyState(AlarmStatus(3))

        testScheduler.advanceTimeBy(20, TimeUnit.SECONDS)

        verify(playSoundUseCase, times(4)).execute()
    }

    @Test
    fun handles_empty_state() {
        alarmActivityPresenter.applyState(null)

        testScheduler.advanceTimeBy(20, TimeUnit.SECONDS)

        verify(playSoundUseCase, times(11)).execute()
    }

    @Test
    fun handles_finished_state() {
        alarmActivityPresenter.applyState(AlarmStatus(0))

        testScheduler.advanceTimeBy(20, TimeUnit.SECONDS)

        verify(playSoundUseCase, never()).execute()
        verify(view, times(1)).goToSendMessageScreen()
    }

    @Test
    fun returns_current_state() {
        alarmActivityPresenter.applyState(AlarmStatus(10))

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)
        val state = alarmActivityPresenter.getState()

        assertThat(state.seconds, equalTo(6L))
    }

    companion object {
        lateinit var testScheduler: TestScheduler

        @BeforeClass
        @JvmStatic
        fun setupClass() {
            testScheduler = TestScheduler()
            RxJavaPlugins.setIoSchedulerHandler { testScheduler }
            RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
            RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
        }
    }

}
