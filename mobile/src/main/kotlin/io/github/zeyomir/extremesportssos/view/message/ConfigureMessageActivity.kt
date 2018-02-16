package io.github.zeyomir.extremesportssos.view.message

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.message.MessagePresenter
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_message.message
import kotlinx.android.synthetic.main.activity_message.next


class ConfigureMessageActivity : AppCompatActivity(), ConfigureMessageView {
    @Inject
    lateinit var presenter: MessagePresenter

    private lateinit var defaultMessage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        defaultMessage = getString(R.string.configure_message_default)
        presenter.bind(this)
        presenter.fetchMessage()
        next.setOnClickListener {
            presenter.saveData(message.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun setData(message: String?) {
        this.message.setText(if (message.isNullOrEmpty()) defaultMessage else message)
    }

    override fun showMessageEmptyError() {
        message.setText(R.string.configure_message_default)
        Toast.makeText(this, R.string.configure_message_validation_error, Toast.LENGTH_SHORT).show()
    }

    override fun nextScreen() {
        Toast.makeText(this, R.string.configure_message_done, Toast.LENGTH_SHORT).show()
        val i = Intent(this, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
        finish()
    }

}
