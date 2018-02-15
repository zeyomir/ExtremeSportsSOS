package io.github.zeyomir.extremesportssos.presenter


abstract class BasePresenter<View> : BasePresenerInterface<View> {
    protected var view: View? = null
        private set

    override fun bind(view: View) {
        if (this.view != null) {
            throw RuntimeException("Concurrent viwe bind!")
        }
        this.view = view
    }

    override fun unbind() {
        view = null
    }
}
