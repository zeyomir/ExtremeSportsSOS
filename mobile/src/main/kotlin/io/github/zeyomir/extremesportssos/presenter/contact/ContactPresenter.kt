package io.github.zeyomir.extremesportssos.presenter.contact

import io.github.zeyomir.extremesportssos.presenter.BasePresenerInterface
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactView


interface ContactPresenter : BasePresenerInterface<ConfigureContactView> {
    fun fetchContactInfo()
    fun saveData(contactInfo: String, contactName: String)

}