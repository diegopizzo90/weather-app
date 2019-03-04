package com.example.weatherapp.ui

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.ui.weatherfragment.WeatherFragment
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity() {

    private lateinit var permissionDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPermission()
    }

    private fun setPermission() {
        permissionDisposable = TedRx2Permission.with(this)
            .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .setDeniedMessage(R.string.permission_denied_message)
            .request()
            .subscribe { t: TedPermissionResult ->
                if (t.isGranted) startFragment() else createPermissionDialog()
            }
    }

    private fun createPermissionDialog() {
        AlertDialog.Builder(this).setTitle(getString(R.string.permission_dialog))
            .setMessage(getString(R.string.message_dialog))
            .setPositiveButton(getString(R.string.button_dialog_title)) { _, _ -> finish() }
            .create().show()
    }

    override fun onStop() {
        super.onStop()
        permissionDisposable.dispose()
    }

    private fun startFragment() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(WeatherFragment.TAG_WEATHER_FRAGMENT)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                WeatherFragment.newInstance(null),
                WeatherFragment.TAG_WEATHER_FRAGMENT
            ).commit()
        }
    }
}
