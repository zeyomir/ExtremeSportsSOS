package io.github.zeyomir.extremesportssos.di.module

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class TestConfigModule {
    @Provides
    @Named("soundId")
    fun provideSoundId() = R.raw.sound

    @Provides
    @Named("activityRecognitionInterval")
    fun provideActivityRecognitionInterval() = 1_000L

    @Provides
    @Named("timeToTellStillness")
    fun provideTimeToTellStillness() = TimePeriod(2, TimeUnit.SECONDS)

    @Provides
    @Named("timeToWaitBeforeSendingMessage")
    fun provideTimeToWaitBeforeSendingMessage() = 2L

    @Provides
    @Singleton
    fun provideTimberTree(): Timber.Tree = Timber.DebugTree()

    @Provides
    @Singleton
    fun provideCrashlyticsCore(): CrashlyticsCore = CrashlyticsCore.Builder()
            .disabled(true)
            .build()

    @Provides
    @Singleton
    fun provideCrashlytics(crashlyticsCore: CrashlyticsCore): Crashlytics = Crashlytics.Builder()
            .core(crashlyticsCore)
            .build()

}
