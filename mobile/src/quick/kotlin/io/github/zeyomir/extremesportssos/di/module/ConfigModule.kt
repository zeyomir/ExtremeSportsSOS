package io.github.zeyomir.extremesportssos.di.module

import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class ConfigModule {
    @Provides
    @Named("soundId")
    fun provideSoundId() = R.raw.sound

    @Provides
    @Named("activityRecognitionInterval")
    fun provideActivityRecognitionInterval() = 5_000L

    @Provides
    @Named("timeToTellStillness")
    fun provideTimeToTellStillness() = TimePeriod(10, TimeUnit.SECONDS)

    @Provides
    @Named("timeToWaitBeforeSendingMessage")
    fun provideTimeToWaitBeforeSendingMessage() = 10L

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree = Timber.DebugTree()

}
