package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.zeyomir.extremesportssos.view.MainActivity


@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}