package io.github.zeyomir.extremesportssos.di.module

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.data.database.PersistentRepository
import io.github.zeyomir.extremesportssos.data.device.sms.SmsManager
import io.github.zeyomir.extremesportssos.data.device.sms.SmsService
import io.github.zeyomir.extremesportssos.data.device.sound.SoundManager
import io.github.zeyomir.extremesportssos.data.device.sound.SoundService
import io.github.zeyomir.extremesportssos.data.sensors.ActivityDetectionService
import io.github.zeyomir.extremesportssos.data.sensors.LocationService
import io.github.zeyomir.extremesportssos.data.sensors.SensorsRepository
import io.github.zeyomir.extremesportssos.domain.driver.SmsDriver
import io.github.zeyomir.extremesportssos.domain.driver.SoundDriver
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class TestDataModule {
    @Provides
    @Singleton
    internal fun provideKeyValueService(): KeyValueService = mock()

    @Provides
    @Singleton
    internal fun provideLocalRepository(keyValueService: KeyValueService): LocalRepository = PersistentRepository(keyValueService)


    @Provides
    @Singleton
    internal fun provideActivityDetectionService(): ActivityDetectionService = mock()

    @Provides
    @Singleton
    internal fun provideLocationService(): LocationService = mock()

    @Provides
    @Singleton
    internal fun provideLocationRepository(activityDetectionService: ActivityDetectionService, @Named("timeToTellStillness") timeToTellStillness: TimePeriod, locationService: LocationService): LocationRepository = SensorsRepository(activityDetectionService, timeToTellStillness, locationService)


    @Provides
    @Singleton
    internal fun provideSoundService(): SoundService = mock()

    @Provides
    @Singleton
    internal fun provideSoundManager(soundService: SoundService, @Named("soundId") soundId: Int): SoundDriver = SoundManager(soundService, soundId)


    @Provides
    @Singleton
    internal fun provideSmsService(): SmsService = mock()

    @Provides
    @Singleton
    internal fun provideSmsDriver(smsService: SmsService): SmsDriver = SmsManager(smsService)
}