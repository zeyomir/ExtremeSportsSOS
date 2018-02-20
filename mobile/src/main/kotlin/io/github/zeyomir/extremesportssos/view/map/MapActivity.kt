package io.github.zeyomir.extremesportssos.view.map

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.map.MapPresenter
import io.github.zeyomir.extremesportssos.view.alarm.AlarmActivity
import io.github.zeyomir.extremesportssos.view.main.MainActivity
import io.github.zeyomir.extremesportssos.view.send.SendMessageActivity
import kotlinx.android.synthetic.main.activity_map.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject


@RuntimePermissions
class MapActivity : AppCompatActivity(), MapView {
    @Inject
    lateinit var presenter: MapPresenter
    private val REQUEST_CHECK_GPS_SETTINGS: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        presenter.bind(this)
    }

    override fun onStart() {
        super.onStart()
        checkGpsSettingsWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_GPS_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.startMonitoringLocation()
            } else {
                Toast.makeText(this, R.string.map_gps_off, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)
    fun checkGpsSettings() {
        val testLocationRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY))
                .build()

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(testLocationRequest)

        task.addOnSuccessListener(this) {
            presenter.startMonitoringLocation()
        }

        task.addOnFailureListener(this) { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(this@MapActivity, REQUEST_CHECK_GPS_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.e("MapActivity", sendEx.message)
                }
            }
        }
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
        val i = Intent(this, AlarmActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun goToSendMessageScreen() {
        val i = Intent(this, SendMessageActivity::class.java)
        startActivity(i)
        finish()
    }
}
