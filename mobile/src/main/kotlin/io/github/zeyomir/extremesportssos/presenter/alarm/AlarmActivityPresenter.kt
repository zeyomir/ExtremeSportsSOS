package io.github.zeyomir.extremesportssos.presenter.alarm

import io.github.zeyomir.extremesportssos.domain.usecase.PlaySoundUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.alarm.AlarmView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class AlarmActivityPresenter @Inject constructor(private val playSound: PlaySoundUseCase, private val timeToWait: Long) : BasePresenter<AlarmView>(), AlarmPresenter {
    private lateinit var state: AlarmStatus

    override fun applyState(newState: AlarmStatus?) {
        state = newState ?: AlarmStatus(timeToWait)

        if (state.seconds > 0) {
            startAlarm(state.seconds)
        } else {
            view?.goToSendMessageScreen()
        }
    }

    override fun getState(): AlarmStatus {
        return state
    }

    private fun startAlarm(seconds: Long) {
        val timer = Observable.interval(1, TimeUnit.SECONDS)
                .take(seconds + 1)
                .map { (seconds - it).toInt() }

        val timerWithSound = Observable.using({
            playSound.prepare()
            playSound
        }, { playSound ->
            timer.doOnNext { playSound.execute() }
        }, {
            it.finish()
        })

        compositeDisposable.add(timerWithSound
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state.seconds = it.toLong()
                    view?.updateTimer(it)
                }, {
                    Timber.e(it)
                }, {
                    view?.goToSendMessageScreen()
                }))
    }
}
