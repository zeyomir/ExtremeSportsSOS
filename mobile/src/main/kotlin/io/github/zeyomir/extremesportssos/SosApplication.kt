package io.github.zeyomir.extremesportssos

import android.app.Activity
import android.app.Application
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.zeyomir.extremesportssos.di.component.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject


class SosApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var timberTree: Timber.Tree

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initLeakCanary()
    }

    private fun initDagger() =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
                    .inject(this)

    private fun initTimber() {
        Timber.plant(timberTree)
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}
