package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.zeyomir.extremesportssos.view.alarm.AlarmActivity
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import io.github.zeyomir.extremesportssos.view.map.MapActivity
import io.github.zeyomir.extremesportssos.view.message.ConfigureMessageActivity
import io.github.zeyomir.extremesportssos.view.send.SendMessageActivity
import io.github.zeyomir.extremesportssos.view.welcome.WelcomeActivity


@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector
    abstract fun bindContactActivity(): ConfigureContactActivity

    @ContributesAndroidInjector
    abstract fun bindMessageActivity(): ConfigureMessageActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMapActivity(): MapActivity

    @ContributesAndroidInjector
    abstract fun bindAlarmActivity(): AlarmActivity

    @ContributesAndroidInjector
    abstract fun bindSendMessageActivity(): SendMessageActivity
}
