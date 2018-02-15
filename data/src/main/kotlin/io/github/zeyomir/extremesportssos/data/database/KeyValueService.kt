package io.github.zeyomir.extremesportssos.data.database

import io.github.zeyomir.extremesportssos.domain.entity.SosContact


internal interface KeyValueService {
    fun getSosContact(): SosContact?
    fun getSosMessage(): String?
    fun saveSosContact(contact: SosContact)
    fun saveSosMessage(message: String)
}