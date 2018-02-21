package io.github.zeyomir.extremesportssos.data.sensors.service

import android.location.Location
import io.github.zeyomir.extremesportssos.data.sensors.LocationService
import io.github.zeyomir.extremesportssos.domain.entity.Coordinates
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject


class PlayServicesLocationService @Inject constructor(private val observable: Observable<Location>) : LocationService {
    override fun current(): Single<Coordinates> {
        return observable
                .map { Coordinates(it.latitude, it.longitude) }
                .doOnNext { Timber.v("Retrieved current location: %f | %f", it.latitude, it.longitude) }
                .firstOrError()
    }
}
