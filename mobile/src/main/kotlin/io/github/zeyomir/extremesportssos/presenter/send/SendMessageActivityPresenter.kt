package io.github.zeyomir.extremesportssos.presenter.send

import io.github.zeyomir.extremesportssos.domain.usecase.SendSosMessageUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.send.SendMessageView
import timber.log.Timber
import javax.inject.Inject


class SendMessageActivityPresenter @Inject constructor(private val sendSosMessage: SendSosMessageUseCase) : BasePresenter<SendMessageView>(), SendMessagePresenter {
    override fun sendMessage() {
        view?.showSending()
        compositeDisposable.add(sendSosMessage.execute()
                .subscribe({
                    view?.showSent()
                }, {
                    Timber.e(it)
                }))
    }
}
