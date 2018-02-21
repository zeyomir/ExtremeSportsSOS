package io.github.zeyomir.extremesportssos.domain.repository

import io.github.zeyomir.extremesportssos.domain.entity.SosContact


interface LocalRepository {
    fun hasConfig(): Boolean

    fun fetchContact(): SosContact?
    fun saveContact(sosContact: SosContact)

    fun fetchMessage(): String?
    fun saveMessage(message: String)
}
