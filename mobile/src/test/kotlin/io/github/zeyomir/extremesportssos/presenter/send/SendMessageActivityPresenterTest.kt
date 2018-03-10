package io.github.zeyomir.extremesportssos.presenter.send

import com.nhaarman.mockito_kotlin.*
import io.github.zeyomir.extremesportssos.domain.usecase.SendSosMessageUseCase
import io.github.zeyomir.extremesportssos.view.send.SendMessageView
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class SendMessageActivityPresenterTest {
    private lateinit var sendMessageActivityPresenter: SendMessageActivityPresenter
    private lateinit var sendSosMessageUseCase: SendSosMessageUseCase
    private lateinit var view: SendMessageView

    @Before
    fun setUp() {
        sendSosMessageUseCase = mock()
        whenever(sendSosMessageUseCase.execute()).thenReturn(Completable.complete())

        sendMessageActivityPresenter = SendMessageActivityPresenter(sendSosMessageUseCase)
        view = mock()
        sendMessageActivityPresenter.bind(view)
    }

    @Test
    fun handles_empty_state() {
        sendMessageActivityPresenter.applyState(null)

        verify(view, times(1)).showSending()
        verify(sendSosMessageUseCase, times(1)).execute()
        verify(view, times(1)).showSent()
    }

    @Test
    fun handles_not_sent_state() {
        sendMessageActivityPresenter.applyState(SentStatus())

        verify(view, times(1)).showSending()
        verify(sendSosMessageUseCase, times(1)).execute()
        verify(view, times(1)).showSent()
    }

    @Test
    fun handles_sent_state() {
        val newState = SentStatus()
        newState.sent = true

        sendMessageActivityPresenter.applyState(newState)

        verify(view, never()).showSending()
        verify(sendSosMessageUseCase, never()).execute()
        verify(view, times(1)).showSent()
    }

    @Test
    fun updates_state() {
        sendMessageActivityPresenter.applyState(SentStatus())

        val state = sendMessageActivityPresenter.getState()

        assertTrue("message was not sent!", state.sent)
    }
}
