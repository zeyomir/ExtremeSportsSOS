package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlertOnUserIsStillUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    fun execute(): Maybe<ActivityType> {
        return locationRepository.detectStillness()
                .firstElement()
    }
}
