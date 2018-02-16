package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.domain.usecase.*
import io.github.zeyomir.extremesportssos.presenter.contact.ConfigureContactActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.contact.ContactPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import io.github.zeyomir.extremesportssos.presenter.map.MapActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.map.MapPresenter
import io.github.zeyomir.extremesportssos.presenter.welcome.WelcomeActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.welcome.WelcomePresenter
import io.github.zeyomir.extremesportssos.presenter.message.ConfigureMessageActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.message.MessagePresenter
import javax.inject.Singleton


@Module
class PresentationModule {
    @Provides
    @Singleton
    fun provideWelcomePresenter(checkConfig: CheckHasConfigUseCase): WelcomePresenter {
        return WelcomeActivityPresenter(checkConfig)
    }

    @Provides
    @Singleton
    fun provideContactPresenter(fetchContactUseCase: FetchContactUseCase, saveContactUseCase: SaveContactUseCase): ContactPresenter {
        return ConfigureContactActivityPresenter(fetchContactUseCase, saveContactUseCase)
    }

    @Provides
    @Singleton
    fun provideMessagePresenter(fetchMessage: FetchMessageUseCase, saveMessage: SaveMessageUseCase): MessagePresenter {
        return ConfigureMessageActivityPresenter(fetchMessage, saveMessage)
    }

    @Provides
    @Singleton
    fun provideMainPresenter(fetchContact: FetchContactUseCase, fetchMessage: FetchMessageUseCase): MainPresenter {
        return MainActivityPresenter(fetchContact, fetchMessage)
    }

    @Provides
    @Singleton
    fun provideMapPresenter(alertOnUserIsStill: AlertOnUserIsStillUseCase): MapPresenter {
        return MapActivityPresenter(alertOnUserIsStill)
    }
}
