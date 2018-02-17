package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SaveMessageUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(message: String) = localRepository.saveMessage(message)
}
