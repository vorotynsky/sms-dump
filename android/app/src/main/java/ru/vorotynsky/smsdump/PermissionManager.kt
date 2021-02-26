package ru.vorotynsky.smsdump

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

object PermissionManager {
    fun hasReadSmsPermission(context: Context) =
            ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED

    fun logPermissionGrantResult(grantResults: IntArray, permissions: Array<out String>) {
        grantResults.zip(permissions).forEach {
            if (it.first == PackageManager.PERMISSION_GRANTED)
                Log.d("PermissionResult", "${it.second} granted.")
        }
    }

    public fun grantPermissions(activity: Activity, permissions: Array<out String>, resultCode: Int) {
        val filtered = permissions.filter {
            when (it) {
                Manifest.permission.READ_SMS -> !hasReadSmsPermission(activity)
                else -> true
            }
        }.toTypedArray()

        if (filtered.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, filtered, resultCode)
        }
    }
}
