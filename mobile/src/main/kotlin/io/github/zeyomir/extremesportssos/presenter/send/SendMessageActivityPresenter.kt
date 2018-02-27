package io.github.zeyomir.extremesportssos.presenter.send

import io.github.zeyomir.extremesportssos.domain.usecase.SendSosMessageUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.send.SendMessageView
import timber.log.Timber
import javax.inject.Inject


class SendMessageActivityPresenter @Inject constructor(private val sendSosMessage: SendSosMessageUseCase) : BasePresenter<SendMessageView>(), SendMessagePresenter {
    private lateinit var state: SentStatus

    override fun applyState(newState: SentStatus?) {
        state = newState ?: SentStatus()

        if (state.sent) {
            view?.showSent()
        } else {
            sendMessage()
        }
    }

    override fun getState(): SentStatus {
        return state
    }

    private fun sendMessage() {
        view?.showSending()
        compositeDisposable.add(sendSosMessage.execute()
                .subscribe({
                    view?.showSent()
                    this.state.sent = true
                }, {
                    Timber.e(it)
                }))
    }
}
