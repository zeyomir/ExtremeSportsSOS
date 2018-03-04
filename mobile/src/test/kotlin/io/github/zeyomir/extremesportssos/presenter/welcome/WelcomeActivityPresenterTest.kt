package io.github.zeyomir.extremesportssos.presenter.welcome

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.github.zeyomir.extremesportssos.domain.usecase.CheckHasConfigUseCase
import io.github.zeyomir.extremesportssos.view.welcome.WelcomeView
import org.junit.Before
import org.junit.Test
import org.mockito.internal.verification.VerificationModeFactory

class WelcomeActivityPresenterTest {
    private lateinit var checkHasConfigUseCase: CheckHasConfigUseCase
    private lateinit var view: WelcomeView
    private lateinit var welcomeActivityPresenter: WelcomeActivityPresenter

    @Before
    fun setUp() {
        checkHasConfigUseCase = mock()
        view = mock()
        welcomeActivityPresenter = WelcomeActivityPresenter(checkHasConfigUseCase)
        welcomeActivityPresenter.bind(view)
    }

    @Test
    fun has_config_moves_forward() {
        whenever(checkHasConfigUseCase.execute()).thenReturn(true)

        welcomeActivityPresenter.checkConfig()

        verify(view, VerificationModeFactory.times(1)).goToNextScreen()
        verify(view, VerificationModeFactory.times(0)).showConfigNeededMessage()
    }

    @Test
    fun does_not_have_config_shows_message() {
        whenever(checkHasConfigUseCase.execute()).thenReturn(false)

        welcomeActivityPresenter.checkConfig()

        verify(view, VerificationModeFactory.times(0)).goToNextScreen()
        verify(view, VerificationModeFactory.times(1)).showConfigNeededMessage()
    }

}
