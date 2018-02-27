package io.github.zeyomir.extremesportssos.view.alarm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.alarm.AlarmPresenter
import io.github.zeyomir.extremesportssos.presenter.alarm.AlarmStatus
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import io.github.zeyomir.extremesportssos.view.send.SendMessageActivity
import kotlinx.android.synthetic.main.activity_alarm.*
import timber.log.Timber
import javax.inject.Inject


class AlarmActivity : AppCompatActivity(), AlarmView {
    @Inject
    lateinit var presenter: AlarmPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        presenter.bind(this)
        fine.setOnClickListener {
            Timber.v("User is fine")
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }

        presenter.applyState(lastCustomNonConfigurationInstance as AlarmStatus?)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter.getState()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun updateTimer(i: Int) {
        Timber.v("Time left\t%d", i)
        timer.text = resources.getQuantityString(R.plurals.alarm_seconds, i, i)
    }

    override fun goToSendMessageScreen() {
        val i = Intent(this, SendMessageActivity::class.java)
        startActivity(i)
        finish()
    }
}
