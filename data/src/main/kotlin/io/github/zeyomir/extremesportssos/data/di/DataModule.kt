package io.github.zeyomir.extremesportssos.data.di

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.location.DetectedActivity
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.data.BuildConfig
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.data.database.PersistentRepository
import io.github.zeyomir.extremesportssos.data.database.service.PreferencesService
import io.github.zeyomir.extremesportssos.data.sensors.LocationService
import io.github.zeyomir.extremesportssos.data.sensors.SensorsRepository
import io.github.zeyomir.extremesportssos.data.sensors.service.PlayServicesLocationService
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.activity.config.ActivityParams
import io.nlopez.smartlocation.rx.ObservableFactory
import io.reactivex.Observable
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    internal fun provideKeyValueService(preferences: SharedPreferences): KeyValueService = PreferencesService(preferences)

    @Provides
    @Singleton
    internal fun provideLocalRepository(keyValueService: KeyValueService): LocalRepository = PersistentRepository(keyValueService)

    @Provides
    @Singleton
    internal fun provideSmartLocation(context: Context) = SmartLocation.with(context)

    @Provides
    @Singleton
    internal fun provideActivityRecognitionConfig() = ActivityParams.Builder().setInterval(60_000L).build()

    @Provides
    @Singleton
    internal fun provideActivityRecognitionControll(smartLocation: SmartLocation, activityParams: ActivityParams) = smartLocation.activity().config(activityParams)

    @Provides
    @Singleton
    internal fun provideActivityRecognitionObservable(activityRecognitionControl: SmartLocation.ActivityRecognitionControl) = ObservableFactory.from(activityRecognitionControl)

    @Provides
    @Singleton
    internal fun provideLocationService(observable: Observable<DetectedActivity>): LocationService = PlayServicesLocationService(observable)

    @Provides
    @Singleton
    internal fun provideLocationRepository(service: LocationService): LocationRepository = SensorsRepository(service)
}
