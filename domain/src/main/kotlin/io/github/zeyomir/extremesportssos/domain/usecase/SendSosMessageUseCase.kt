package io.github.zeyomir.extremesportssos.domain.usecase

import io.github.zeyomir.extremesportssos.domain.driver.SmsDriver
import io.github.zeyomir.extremesportssos.domain.repository.LocalRepository
import io.github.zeyomir.extremesportssos.domain.repository.LocationRepository
import io.reactivex.Completable
import javax.inject.Inject


class SendSosMessageUseCase @Inject constructor(
        private val localRepository: LocalRepository,
        private val locationRepository: LocationRepository,
        private val smsDriver: SmsDriver
) {
    fun execute(): Completable {
        val contact = localRepository.fetchContact()
        return locationRepository.getCurrentCoordinates()
                .map { "https://www.google.com/maps/search/?api=1&query=${it.latitude},${it.longitude}" }
                .map { "${localRepository.fetchMessage()}\n$it" }
                .flatMapCompletable { smsDriver.sendSms(contact!!, it) }
    }
}
