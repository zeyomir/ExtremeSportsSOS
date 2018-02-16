package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.reactivex.Observable


interface LocationService {
    fun userMovingUpdates(): Observable<ActivityType>
}
