package io.github.zeyomir.extremesportssos.presenter.main

import io.github.zeyomir.extremesportssos.domain.usecase.CheckHasContactUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.main.MainView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityPresenter @Inject constructor(private val checkHasContact: CheckHasContactUseCase)
    : BasePresenter<MainView>(), MainPresenter {

    override fun checkConfig() {
        if (checkHasContact.execute()) {
            view?.goToNextScreen()
        } else {
            view?.showConfigNeededMessage()
        }
    }
}