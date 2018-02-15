package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.domain.usecase.CheckHasContactUseCase
import io.github.zeyomir.extremesportssos.domain.usecase.FetchContactUseCase
import io.github.zeyomir.extremesportssos.domain.usecase.SaveContactUseCase
import io.github.zeyomir.extremesportssos.presenter.contact.ConfigureContactActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.contact.ContactPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainActivityPresenter
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import javax.inject.Singleton


@Module
class PresentationModule {
    @Provides
    @Singleton
    fun provideMainPresenter(checkConfig: CheckHasContactUseCase): MainPresenter {
        return MainActivityPresenter(checkConfig)
    }

    @Provides
    @Singleton
    fun provideContactPresenter(fetchContactUseCase: FetchContactUseCase, saveContactUseCase: SaveContactUseCase): ContactPresenter {
        return ConfigureContactActivityPresenter(fetchContactUseCase, saveContactUseCase)
    }
}