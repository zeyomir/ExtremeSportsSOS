package io.github.zeyomir.extremesportssos.data.device.sms.service

import android.telephony.SmsManager
import io.github.zeyomir.extremesportssos.data.device.sms.SmsService
import timber.log.Timber
import javax.inject.Inject


class AndroidSmsService @Inject constructor(private val smsManager: SmsManager) : SmsService {
    override fun send(to: String, message: String) {
        val dividedMessage = smsManager.divideMessage(message)
        Timber.v("Sending message >>>%s<<< to %s", message, to)
        smsManager.sendMultipartTextMessage(to, null, dividedMessage, null, null)
    }
}
