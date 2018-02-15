package io.github.zeyomir.extremesportssos.presenter.welcome

import io.github.zeyomir.extremesportssos.domain.usecase.CheckHasConfigUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.welcome.WelcomeView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WelcomeActivityPresenter @Inject constructor(private val checkHasConfig: CheckHasConfigUseCase)
    : BasePresenter<WelcomeView>(), WelcomePresenter {

    override fun checkConfig() {
        if (checkHasConfig.execute()) {
            view?.goToNextScreen()
        } else {
            view?.showConfigNeededMessage()
        }
    }
}
