package io.github.zeyomir.extremesportssos.presenter.welcome

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.welcome.WelcomeView


interface WelcomePresenter : BasePresenerInterface<WelcomeView> {
    fun checkConfig()
}
