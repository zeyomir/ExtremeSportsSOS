package io.github.zeyomir.extremesportssos.presenter


abstract class MainPresenter<View> {
    protected var view: View? = null

    fun bind(view: View) {
        if (this.view != null) {
            throw RuntimeException("Concurrent viwe bind!")
        }
        this.view = view
        afterBind()
    }

    open fun afterBind() {

    }

    fun unbind() {
        view = null
    }
}