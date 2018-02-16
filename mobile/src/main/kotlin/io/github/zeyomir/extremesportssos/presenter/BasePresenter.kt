package io.github.zeyomir.extremesportssos.presenter

import io.reactivex.disposables.CompositeDisposable


abstract class BasePresenter<View> : BasePresenerInterface<View> {
    protected val compositeDisposable = CompositeDisposable()

    protected var view: View? = null
        private set

    override fun bind(view: View) {
        if (this.view != null) {
            throw RuntimeException("Concurrent viwe bind!")
        }
        this.view = view
    }

    override fun unbind() {
        compositeDisposable.clear()
        view = null
    }
}
