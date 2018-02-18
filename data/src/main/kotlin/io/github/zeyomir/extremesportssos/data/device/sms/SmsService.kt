package io.github.zeyomir.extremesportssos.data.device.sms


interface SmsService {
    fun send(to: String, message: String)
}
