package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.entity.Coordinates
import io.github.zeyomir.extremesportssos.domain.entity.TimePeriod
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class SensorsRepository @Inject constructor(
        private val activityDetectionService: ActivityDetectionService,
        private val timeToTellStillness: TimePeriod,
        private val locationService: LocationService
) : LocationRepository {
    override fun detectStillness(): Observable<ActivityType> {
        return activityDetectionService.userMovingUpdates()
                .distinctUntilChanged()
                .debounce(timeToTellStillness.amount, timeToTellStillness.unit)
                .filter { it == ActivityType.STILL }
    }

    override fun getCurrentCoordinates(): Single<Coordinates> {
        return locationService.current()
    }

}
