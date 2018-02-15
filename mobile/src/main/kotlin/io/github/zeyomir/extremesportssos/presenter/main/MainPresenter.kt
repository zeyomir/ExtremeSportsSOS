package io.github.zeyomir.extremesportssos.presenter.main

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.main.MainView


interface MainPresenter: BasePresenerInterface<MainView> {
    fun fetchData()
}
