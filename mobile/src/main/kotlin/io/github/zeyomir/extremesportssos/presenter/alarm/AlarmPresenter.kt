package io.github.zeyomir.extremesportssos.presenter.alarm

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.alarm.AlarmView


interface AlarmPresenter : BasePresenerInterface<AlarmView> {
    fun applyState(newState: AlarmStatus?)
    fun getState(): AlarmStatus
}
