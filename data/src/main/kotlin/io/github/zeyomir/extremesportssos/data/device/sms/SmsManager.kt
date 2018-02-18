package io.github.zeyomir.extremesportssos.data.device.sms

import io.github.zeyomir.extremesportssos.domain.driver.SmsDriver
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.reactivex.Completable
import javax.inject.Inject


class SmsManager @Inject constructor(private val smsService: SmsService): SmsDriver {
    override fun sendSms(contact: SosContact, message: String): Completable {
        return Completable.fromRunnable {
            smsService.send(contact.contactInfo, message)
        }
    }
}
