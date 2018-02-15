package io.github.zeyomir.extremesportssos.view.contact


interface ConfigureContactView {
    fun setData(contactInfo: String?, contactName: String?)
    fun showContactInfoEmptyError()
    fun nextScreen()
}