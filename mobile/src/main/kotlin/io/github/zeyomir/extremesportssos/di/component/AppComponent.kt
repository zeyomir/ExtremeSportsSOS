package io.github.zeyomir.extremesportssos.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.github.zeyomir.extremesportssos.SosApplication
import io.github.zeyomir.extremesportssos.data.di.DataModule
import io.github.zeyomir.extremesportssos.di.module.AppModule
import io.github.zeyomir.extremesportssos.di.module.BuildersModule
import io.github.zeyomir.extremesportssos.di.module.ConfigModule
import io.github.zeyomir.extremesportssos.di.module.PresentationModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ConfigModule::class, DataModule::class, AppModule::class, BuildersModule::class, PresentationModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SosApplication): Builder

        fun build(): AppComponent
    }

    fun inject(target: SosApplication)
}
