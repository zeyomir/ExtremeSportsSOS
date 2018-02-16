package io.github.zeyomir.extremesportssos.presenter.map

import android.util.Log
import io.github.zeyomir.extremesportssos.domain.usecase.AlertOnUserIsStillUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.map.MapView
import io.reactivex.android.schedulers.AndroidSchedulers
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
                            Log.e("", "error :(\n${it.message}")
                        }, {
                            Log.d("", "completed without user stillness")
                        })
        )
    }

    override fun helpNeeded() {
        view?.triggerAlarm()
    }
}
