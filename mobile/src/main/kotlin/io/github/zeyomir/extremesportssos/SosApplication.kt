package io.github.zeyomir.extremesportssos

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.zeyomir.extremesportssos.di.component.DaggerAppComponent
import javax.inject.Inject


class SosApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
                    .inject(this)
}
