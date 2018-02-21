package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CheckHasConfigUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(): Boolean {
        val hasConfig = localRepository.hasConfig()
        Timber.v("User has config: %s", hasConfig)
        return hasConfig
    }
}
