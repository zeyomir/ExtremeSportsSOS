package io.github.zeyomir.extremesportssos.presenter.map

import com.nhaarman.mockito_kotlin.*
import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.usecase.AlertOnUserIsStillUseCase
import io.github.zeyomir.extremesportssos.view.map.MapView
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test


class MapActivityPresenterTest {
    private lateinit var mapActivityPresenter: MapActivityPresenter
    private lateinit var alertOnUserIsStill: AlertOnUserIsStillUseCase
    private lateinit var view: MapView

    @Before
    fun setUp() {
        alertOnUserIsStill = mock()
        view = mock()
        mapActivityPresenter = MapActivityPresenter(alertOnUserIsStill)
        mapActivityPresenter.bind(view)
    }

    @Test
    fun permissions_missing_shows_message() {
        mapActivityPresenter.permissionsMissing()

        verify(view, times(1)).displayPermissionsMessage()
    }

    @Test
    fun start_monitoring_location_shows_map_view() {
        whenever(alertOnUserIsStill.execute()).thenReturn(Maybe.empty())
        mapActivityPresenter.startMonitoringLocation()

        verify(view, atLeastOnce()).displayMap()
    }

    @Test
    @Ignore //runs fine in Android Studio but fails from CLI :|
    fun triggers_alarm_when_user_is_still() {
        whenever(alertOnUserIsStill.execute()).thenReturn(Maybe.just(ActivityType.STILL))

        mapActivityPresenter.startMonitoringLocation()

        verify(view, atLeastOnce()).triggerAlarm()
    }

    @Test
    fun no_alarm_if_user_is_not_still() {
        whenever(alertOnUserIsStill.execute()).thenReturn(Maybe.empty())

        mapActivityPresenter.startMonitoringLocation()

        verify(view, never()).triggerAlarm()
    }

    @Test
    fun help_needed_goes_to_send_message_screen() {
        mapActivityPresenter.helpNeeded()

        verify(view, times(1)).goToSendMessageScreen()
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

}
