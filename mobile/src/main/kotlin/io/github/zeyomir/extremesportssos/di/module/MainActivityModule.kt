package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.view.MagicHelloService


@Module
class MainActivityModule {
    @Provides
    fun providesMagicHelloService() = MagicHelloService()

}