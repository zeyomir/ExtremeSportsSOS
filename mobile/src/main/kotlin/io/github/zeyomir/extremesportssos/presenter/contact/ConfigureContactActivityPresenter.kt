package io.github.zeyomir.extremesportssos.presenter.contact

import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.domain.usecase.FetchContactUseCase
import io.github.zeyomir.extremesportssos.domain.usecase.SaveContactUseCase
import io.github.zeyomir.extremesportssos.presenter.BasePresenter
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactView
import javax.inject.Inject


class ConfigureContactActivityPresenter @Inject constructor(
        private val fetchContact: FetchContactUseCase,
        private val saveContact: SaveContactUseCase)
    : BasePresenter<ConfigureContactView>(), ContactPresenter {
    override fun fetchContactInfo() {
        val sosContact: SosContact? = fetchContact.execute()
        view?.setData(sosContact?.contactInfo, sosContact?.contactName)
    }

    override fun saveData(contactInfo: String, contactName: String) {
        if (contactInfo.isEmpty())
            view?.showContactInfoEmptyError()
        else {
            saveContact.execute(SosContact(contactInfo, contactName))
            view?.nextScreen()
        }
    }
}