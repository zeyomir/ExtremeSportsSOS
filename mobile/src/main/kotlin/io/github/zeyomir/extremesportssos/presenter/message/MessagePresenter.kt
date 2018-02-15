package io.github.zeyomir.extremesportssos.presenter.message

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.message.ConfigureMessageView


interface MessagePresenter : BasePresenerInterface<ConfigureMessageView> {
    fun fetchMessage()
    fun saveData(message: String)
}