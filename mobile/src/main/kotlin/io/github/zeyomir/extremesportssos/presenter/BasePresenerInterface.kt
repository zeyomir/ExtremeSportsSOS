package io.github.zeyomir.extremesportssos.presenter

interface BasePresenerInterface<View> {
    fun bind(view: View)
    fun unbind()
}