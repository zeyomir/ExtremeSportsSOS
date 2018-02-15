package io.github.zeyomir.extremesportssos.view.message


interface ConfigureMessageView {
    fun setData(message: String?)
    fun showMessageEmptyError()
    fun nextScreen()
}