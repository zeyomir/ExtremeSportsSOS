package io.github.zeyomir.extremesportssos.presenter.message

import io.github.zeyomir.extremesportssos.domain.usecase.FetchMessageUseCase
import io.github.zeyomir.extremesportssos.domain.usecase.SaveMessageUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.message.ConfigureMessageView
import javax.inject.Inject


class ConfigureMessageActivityPresenter @Inject constructor(
        private val fetchMessage: FetchMessageUseCase,
        private val saveMessage: SaveMessageUseCase)
    : BasePresenter<ConfigureMessageView>(), MessagePresenter {
    override fun fetchMessage() {
        val message: String? = fetchMessage.execute()
        view?.setData(message)
    }

    override fun saveData(message: String) {
        if (message.isEmpty())
            view?.showMessageEmptyError()
        else {
            saveMessage.execute(message)
            view?.nextScreen()
        }
    }
}