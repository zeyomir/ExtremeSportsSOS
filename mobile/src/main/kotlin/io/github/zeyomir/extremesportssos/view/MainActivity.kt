package io.github.zeyomir.extremesportssos.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.view
import kotlinx.android.synthetic.main.activity_main.button
import javax.inject.Inject


class MainActivity: AppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
    }

    override fun goToNextScreen() {
        finish()
    }

    override fun showConfigNeededMessage() {
        view.visibility = View.VISIBLE
        button.setOnClickListener { Toast.makeText(this, "TADA!", Toast.LENGTH_SHORT).show() }
    }
}
