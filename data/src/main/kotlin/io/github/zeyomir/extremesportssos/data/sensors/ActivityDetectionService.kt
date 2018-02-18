package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.reactivex.Observable


interface ActivityDetectionService {
    fun userMovingUpdates(): Observable<ActivityType>
}
