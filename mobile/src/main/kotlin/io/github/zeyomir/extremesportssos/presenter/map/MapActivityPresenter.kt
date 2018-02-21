package io.github.zeyomir.extremesportssos.presenter.map

import io.github.zeyomir.extremesportssos.domain.usecase.AlertOnUserIsStillUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.map.MapView
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject


class MapActivityPresenter @Inject constructor(private val alertOnUserIsStill: AlertOnUserIsStillUseCase) : BasePresenter<MapView>(), MapPresenter {

    override fun permissionsMissing() {
        view?.displayPermissionsMessage()
    }

    override fun startMonitoringLocation() {
        view?.displayMap()
        compositeDisposable.add(
                alertOnUserIsStill.execute()
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            view?.triggerAlarm()
                        }, {
                            Timber.e(it)
                        }, {
                            Timber.w("completed without user stillness")
                        })
        )
    }

    override fun helpNeeded() {
        view?.goToSendMessageScreen()
    }
}
