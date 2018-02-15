package io.github.zeyomir.extremesportssos.presenter.main

import io.github.zeyomir.extremesportssos.domain.usecase.FetchContactUseCase
import io.github.zeyomir.extremesportssos.domain.usecase.FetchMessageUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.main.MainView
import javax.inject.Inject


class MainActivityPresenter @Inject constructor(
        private val fetchContact: FetchContactUseCase,
        private val fetchMessage: FetchMessageUseCase)
    : BasePresenter<MainView>(), MainPresenter {

    override fun fetchData() {
        val contact = fetchContact.execute()
        val message = fetchMessage.execute()
        view?.setCurrentConfig(contact!!, message!!)
    }
}
