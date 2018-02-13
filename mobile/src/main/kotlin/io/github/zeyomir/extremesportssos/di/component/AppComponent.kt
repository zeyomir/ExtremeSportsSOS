package io.github.zeyomir.extremesportssos.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.github.zeyomir.extremesportssos.SosApplication
import io.github.zeyomir.extremesportssos.di.module.AppModule
import io.github.zeyomir.extremesportssos.di.module.BuildersModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SosApplication): Builder

        fun build(): AppComponent
    }

    fun inject(target: SosApplication)
}
