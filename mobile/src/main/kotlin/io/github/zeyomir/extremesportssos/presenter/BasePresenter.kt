package io.github.zeyomir.extremesportssos.presenter

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


abstract class BasePresenter<View> : BasePresenerInterface<View> {
    protected val compositeDisposable = CompositeDisposable()

    protected var view: View? = null
        private set

    override fun bind(view: View) {
        if (this.view != null) {
            throw RuntimeException("Concurrent view bind!")
        }
        Timber.v("View bound")
        this.view = view
    }

    override fun unbind() {
        compositeDisposable.clear()
        view = null
        Timber.v("View unbound and disposables cleared")
    }
}
