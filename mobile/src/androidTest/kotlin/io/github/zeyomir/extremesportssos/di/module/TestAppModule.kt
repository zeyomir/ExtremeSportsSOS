package io.github.zeyomir.extremesportssos.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.MockApplication

@Module
class TestAppModule {
    @Provides
    fun provideContext(app: MockApplication): Context = app.applicationContext
}