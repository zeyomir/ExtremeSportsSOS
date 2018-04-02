package io.github.zeyomir.extremesportssos.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.github.zeyomir.extremesportssos.MockApplication
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.di.module.*
import javax.inject.Singleton


@Singleton
@Component(modules = [TestDataModule::class, TestConfigModule::class, TestAppModule::class, BuildersModule::class, AndroidSupportInjectionModule::class, PresentationModule::class])
interface TestComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MockApplication): Builder

        fun build(): TestComponent
    }

    fun inject(target: MockApplication)

    fun keyValueService(): KeyValueService
}