package io.github.zeyomir.extremesportssos.presenter

import io.github.zeyomir.extremesportssos.domain.usecase.CheckInitialConfigUseCase
import io.github.zeyomir.extremesportssos.view.MainView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityPresenter @Inject constructor(private val checkConfig: CheckInitialConfigUseCase) : MainPresenter<MainView>() {
    override fun afterBind() {
        if (checkConfig.execute()) {
            view?.goToNextScreen()
        } else {
            view?.showConfigNeededMessage()
        }
    }
}