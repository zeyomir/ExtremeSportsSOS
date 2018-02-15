package io.github.zeyomir.extremesportssos.view.welcome

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.welcome.WelcomePresenter
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_welcome.view
import kotlinx.android.synthetic.main.activity_welcome.button
import javax.inject.Inject


class WelcomeActivity : AppCompatActivity(), WelcomeView {
    @Inject
    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        presenter.bind(this)
        presenter.checkConfig()
    }

    override fun goToNextScreen() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
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