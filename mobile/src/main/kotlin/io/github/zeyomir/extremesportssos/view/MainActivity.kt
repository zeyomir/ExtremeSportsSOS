package io.github.zeyomir.extremesportssos.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.common.HelloService
import javax.inject.Inject


class MainActivity: AppCompatActivity() {
    @Inject
    lateinit var helloService: HelloService
    @Inject
    lateinit var magicHelloService: MagicHelloService
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        textView = TextView(this)
        setContentView(textView)
    }

    override fun onResume() {
        super.onResume()
        textView.text = """${helloService.sayHello()}
            |${magicHelloService.sayHello()}""".trimMargin()
    }
}