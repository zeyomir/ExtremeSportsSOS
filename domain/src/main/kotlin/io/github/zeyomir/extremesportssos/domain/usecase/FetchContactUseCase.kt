package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FetchContactUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute() = localRepository.fetchContact()
}
