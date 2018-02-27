package io.github.zeyomir.extremesportssos.view.send

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.send.SendMessagePresenter
import io.github.zeyomir.extremesportssos.presenter.send.SentStatus
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_send_message.*
import javax.inject.Inject


class SendMessageActivity : AppCompatActivity(), SendMessageView {
    @Inject
    lateinit var presenter: SendMessagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        presenter.bind(this)
        fine.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }

        presenter.applyState(lastCustomNonConfigurationInstance as SentStatus?)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter.getState()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun showSending() {
        sending_status.text = getString(R.string.send_message_sending)
    }

    override fun showSent() {
        sending_status.text = getString(R.string.send_message_sent)
        val done = getDrawable(R.drawable.ic_done)
        sending_status.setCompoundDrawables(null, null, done, null)
    }
}

