package io.github.zeyomir.extremesportssos.data.sensors.service

import com.google.android.gms.location.DetectedActivity
import io.github.zeyomir.extremesportssos.data.sensors.ActivityDetectionService
import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject


class PlayServicesActivityDetectionService @Inject constructor(private val acivityObservable: Observable<DetectedActivity>) : ActivityDetectionService {

    override fun userMovingUpdates(): Observable<ActivityType> {
        return acivityObservable
                .map { if (it.type == DetectedActivity.STILL) ActivityType.STILL else ActivityType.MOVING }
                .doOnNext { Timber.v("User is %s", it) }
    }
}
