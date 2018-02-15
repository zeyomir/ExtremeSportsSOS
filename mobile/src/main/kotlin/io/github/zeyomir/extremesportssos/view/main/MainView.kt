package io.github.zeyomir.extremesportssos.view.main

import io.github.zeyomir.extremesportssos.domain.entity.SosContact

interface MainView {
    fun setCurrentConfig(contact: SosContact, message: String)
}
