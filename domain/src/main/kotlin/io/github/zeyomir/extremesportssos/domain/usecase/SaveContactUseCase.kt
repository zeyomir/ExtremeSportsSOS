package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SaveContactUseCase @Inject constructor(private val localRepository: LocalRepository) {
    fun execute(sosContact: SosContact) = localRepository.saveContact(sosContact)
}
