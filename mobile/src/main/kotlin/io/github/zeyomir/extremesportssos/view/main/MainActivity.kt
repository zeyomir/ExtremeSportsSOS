package io.github.zeyomir.extremesportssos.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import io.github.zeyomir.extremesportssos.presenter.main.MainPresenter
import io.github.zeyomir.extremesportssos.view.contact.ConfigureContactActivity
import io.github.zeyomir.extremesportssos.view.map.MapActivity
import kotlinx.android.synthetic.main.activity_main.contact_info
import kotlinx.android.synthetic.main.activity_main.sos_message
import kotlinx.android.synthetic.main.activity_main.start
import kotlinx.android.synthetic.main.activity_main.config
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        presenter.fetchData()
        start.setOnClickListener {
            val i = Intent(this, MapActivity::class.java)
            startActivity(i)
        }
        config.setOnClickListener {
            val i = Intent(this, ConfigureContactActivity::class.java)
            startActivity(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun setCurrentConfig(contact: SosContact, message: String) {
        contact_info.text = getString(R.string.main_contact, parseContact(contact))
        sos_message.text = message
    }

    private fun parseContact(contact: SosContact): String {
        return if (contact.contactName.isNullOrEmpty()) {
            contact.contactInfo
        } else {
            "${contact.contactName} (${contact.contactInfo})"
        }
    }
}
