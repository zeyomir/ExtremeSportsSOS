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
    override fun startAlarm() {
        playSound.prepare()
        compositeDisposable.add(Observable.interval(1, TimeUnit.SECONDS)
                .take(timeToWait + 1)
                .map { (timeToWait - it).toInt() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.updateTimer(it)
                    playSound.execute()
                }, {
                    playSound.finish()
                    Timber.e(it)
                }, {
                    playSound.finish()
                    view?.goToSendMessageScreen()
                }))
    }
}
