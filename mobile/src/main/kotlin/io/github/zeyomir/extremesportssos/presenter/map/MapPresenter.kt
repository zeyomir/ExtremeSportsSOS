package io.github.zeyomir.extremesportssos.presenter.map

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.map.MapView


interface MapPresenter : BasePresenerInterface<MapView> {
    fun permissionsMissing()
    fun startMonitoringLocation()
    fun helpNeeded()
}
