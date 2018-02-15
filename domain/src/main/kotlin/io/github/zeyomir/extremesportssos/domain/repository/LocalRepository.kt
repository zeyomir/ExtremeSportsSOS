package io.github.zeyomir.extremesportssos.domain.repository

import io.github.zeyomir.extremesportssos.domain.entity.SosContact


interface LocalRepository {
    fun hasContact(): Boolean
    fun fetchContact(): SosContact?
    fun saveContact(sosContact: SosContact)
}