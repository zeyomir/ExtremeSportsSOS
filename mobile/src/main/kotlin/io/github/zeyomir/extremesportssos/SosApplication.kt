package io.github.zeyomir.extremesportssos

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import io.github.zeyomir.extremesportssos.di.component.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject


open class SosApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispachingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var timberTree: Timber.Tree
    @Inject
    lateinit var crashlytics: Crashlytics

    override fun activityInjector(): AndroidInjector<Activity> = dispachingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
        initLeakCanary()
        initCrashlytics()
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

    private fun initCrashlytics() {
        Fabric.with(this, crashlytics)
    }
}
