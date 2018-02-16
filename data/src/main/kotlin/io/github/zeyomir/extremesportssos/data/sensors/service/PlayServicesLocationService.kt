package io.github.zeyomir.extremesportssos.data.sensors.service

import com.google.android.gms.location.DetectedActivity
import io.github.zeyomir.extremesportssos.data.sensors.LocationService
import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.reactivex.Observable
import javax.inject.Inject


class PlayServicesLocationService @Inject constructor(private val acivityObservable: Observable<DetectedActivity>) : LocationService {

    override fun userMovingUpdates(): Observable<ActivityType> {
        return acivityObservable
                .map { if (it.type == DetectedActivity.STILL) ActivityType.STILL else ActivityType.MOVING }
    }

}
