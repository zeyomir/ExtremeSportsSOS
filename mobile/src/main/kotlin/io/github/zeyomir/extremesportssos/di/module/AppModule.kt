package io.github.zeyomir.extremesportssos.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.SosApplication
import io.github.zeyomir.extremesportssos.common.HelloService
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(app: SosApplication): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideHelloService() = HelloService()
}