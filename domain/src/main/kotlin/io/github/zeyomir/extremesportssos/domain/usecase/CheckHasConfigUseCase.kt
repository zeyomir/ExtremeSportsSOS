package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject

class CheckHasConfigUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute() = localRepository.hasContact()
}
