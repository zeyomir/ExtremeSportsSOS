package io.github.zeyomir.extremesportssos.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.github.zeyomir.extremesportssos.data.BuildConfig
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.data.database.PersistentRepository
import io.github.zeyomir.extremesportssos.data.database.service.PreferencesService
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
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


}