package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.entity.ActivityType
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Maybe
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlertOnUserIsStillUseCase @Inject constructor(private val locationRepository: LocationRepository) {

    fun execute(): Maybe<ActivityType> {
        return locationRepository.detectStillness()
                .doOnNext { Timber.v("User is still for long time") }
                .firstElement()
    }
}
