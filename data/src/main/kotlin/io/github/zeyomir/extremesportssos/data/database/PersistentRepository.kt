package io.github.zeyomir.extremesportssos.data.database

import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PersistentRepository @Inject constructor(private val keyValueService: KeyValueService) : LocalRepository {
    override fun hasConfig() =
            (keyValueService.getSosContact() != null && keyValueService.getSosMessage() != null)
}