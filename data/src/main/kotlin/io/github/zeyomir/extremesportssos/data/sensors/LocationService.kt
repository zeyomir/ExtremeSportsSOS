package io.github.zeyomir.extremesportssos.data.sensors

import io.github.zeyomir.extremesportssos.domain.entity.Coordinates
import io.reactivex.Single


interface LocationService {
    fun current(): Single<Coordinates>
}
