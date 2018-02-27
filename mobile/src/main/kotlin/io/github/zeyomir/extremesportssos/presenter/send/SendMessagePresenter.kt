package io.github.zeyomir.extremesportssos.presenter.send

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.send.SendMessageView


interface SendMessagePresenter : BasePresenerInterface<SendMessageView> {
    fun applyState(newState: SentStatus?)
    fun getState(): SentStatus
}
