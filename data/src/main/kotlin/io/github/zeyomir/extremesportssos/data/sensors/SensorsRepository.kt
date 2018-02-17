package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Observable
import javax.inject.Inject


class SensorsRepository @Inject constructor(private val locationService: LocationService, private val timeToTellStillness: TimePeriod) : LocationRepository {
    override fun detectStillness(): Observable<ActivityType> {
        return locationService.userMovingUpdates()
                .distinctUntilChanged()
                .debounce(timeToTellStillness.amount, timeToTellStillness.unit)
                .filter { it == ActivityType.STILL}
    }

}
