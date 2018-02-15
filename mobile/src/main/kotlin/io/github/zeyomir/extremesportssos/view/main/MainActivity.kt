package io.github.zeyomir.extremesportssos.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import kotlinx.android.synthetic.main.activity_main.view
import kotlinx.android.synthetic.main.activity_main.button
import javax.inject.Inject


class MainActivity: AppCompatActivity(), MainView {
    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        presenter.checkConfig()
    }

    override fun goToNextScreen() {
        finish()
    }

    override fun showConfigNeededMessage() {
        view.visibility = View.VISIBLE
        button.setOnClickListener {
            val i = Intent(this, ConfigureContactActivity::class.java)
            startActivity(i)
        }
    }
}
