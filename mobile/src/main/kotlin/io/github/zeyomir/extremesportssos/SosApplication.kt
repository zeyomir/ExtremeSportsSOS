package io.github.zeyomir.extremesportssos

import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.zeyomir.extremesportssos.common.HelloService
import io.github.zeyomir.extremesportssos.di.component.DaggerAppComponent
import javax.inject.Inject


class SosApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var helloService: HelloService

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()

        Log.e("APPLICATION", helloService.sayHello())
    }

    private fun initDagger() =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
                    .inject(this)
}