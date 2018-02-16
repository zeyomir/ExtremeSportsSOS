package io.github.zeyomir.extremesportssos.domain.repository

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.reactivex.Observable


interface LocationRepository {
    fun detectStillness(): Observable<ActivityType>
}
