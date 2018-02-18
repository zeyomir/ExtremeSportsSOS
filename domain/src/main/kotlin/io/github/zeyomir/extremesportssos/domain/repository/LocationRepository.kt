package io.github.zeyomir.extremesportssos.domain.repository

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.entity.Coordinates
import io.reactivex.Observable
import io.reactivex.Single


interface LocationRepository {
    fun detectStillness(): Observable<ActivityType>
    fun getCurrentCoordinates(): Single<Coordinates>
}
