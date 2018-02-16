package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SensorsRepository @Inject constructor(private val locationService: LocationService) : LocationRepository {
    override fun detectStillness(): Observable<ActivityType> {
        return locationService.userMovingUpdates()
                .distinctUntilChanged()
                .debounce(5, TimeUnit.MINUTES)
                .filter { it == ActivityType.STILL}
    }

}
