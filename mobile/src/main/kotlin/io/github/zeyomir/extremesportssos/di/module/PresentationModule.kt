package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.domain.usecase.*
import io.github.zeyomir.extremesportssos.presenter.alarm.AlarmActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.alarm.AlarmPresenter
import io.github.zeyomir.extremesportssos.presenter.contact.ConfigureContactActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.contact.ContactPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import io.github.zeyomir.extremesportssos.presenter.map.MapActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.map.MapPresenter
import io.github.zeyomir.extremesportssos.presenter.message.ConfigureMessageActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.message.MessagePresenter
import io.github.zeyomir.extremesportssos.presenter.send.SendMessageActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.send.SendMessagePresenter
import io.github.zeyomir.extremesportssos.presenter.welcome.WelcomeActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.welcome.WelcomePresenter
import javax.inject.Named
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

    @Provides
    @Singleton
    fun provideAlarmPresenter(playSound: PlaySoundUseCase, @Named("timeToWaitBeforeSendingMessage") timeToWait: Long): AlarmPresenter {
        return AlarmActivityPresenter(playSound, timeToWait)
    }

    @Provides
    @Singleton
    fun provideSendMessagePresenter(sendSosMessage: SendSosMessageUseCase): SendMessagePresenter {
        return SendMessageActivityPresenter(sendSosMessage)
    }
}
