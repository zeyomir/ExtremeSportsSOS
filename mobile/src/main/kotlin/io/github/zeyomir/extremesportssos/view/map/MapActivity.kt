package io.github.zeyomir.extremesportssos.view.map

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.map.MapPresenter
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_map.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject


@RuntimePermissions
class MapActivity : AppCompatActivity(), MapView {
    @Inject
    lateinit var presenter: MapPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        presenter.bind(this)
    }

    override fun onStart() {
        super.onStart()
        startMonitoringLocationWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)
    fun startMonitoringLocation() {
        presenter.startMonitoringLocation()
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)
    fun permissionsDenied() {
        presenter.permissionsMissing()
    }

    override fun displayPermissionsMessage() {
        permissions_needed.visibility = View.VISIBLE
        permissions_settings.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivity(intent)
        }
    }

    override fun displayMap() {
        map.visibility = View.VISIBLE

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        help.setOnClickListener { presenter.helpNeeded() }
        finish.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }
    }

    override fun triggerAlarm() {
        Toast.makeText(this, R.string.general_next_screen, Toast.LENGTH_SHORT).show()
    }
}
