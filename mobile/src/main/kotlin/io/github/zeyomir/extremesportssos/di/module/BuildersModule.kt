package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import io.github.zeyomir.extremesportssos.view.message.ConfigureMessageActivity


@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector
    abstract fun bindContactActivity(): ConfigureContactActivity
    @ContributesAndroidInjector
    abstract fun bindMessageActivity(): ConfigureMessageActivity
}