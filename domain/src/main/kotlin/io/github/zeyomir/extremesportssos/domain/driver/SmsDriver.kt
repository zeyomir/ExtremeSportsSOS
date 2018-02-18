package io.github.zeyomir.extremesportssos.domain.driver

import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.reactivex.Completable


interface SmsDriver {
    fun sendSms(contact: SosContact, message: String): Completable
}
