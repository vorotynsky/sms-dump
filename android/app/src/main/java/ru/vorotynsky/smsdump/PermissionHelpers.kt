package ru.vorotynsky.smsdump

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat

fun hasPermission(context: Context, permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

fun logPermissionGrantResult(grantResults: IntArray, permissions: Array<out String>) {
    grantResults.zip(permissions).forEach {
        if (it.first == PackageManager.PERMISSION_GRANTED)
            Log.d("PermissionResult", "${it.second} granted.")
    }
}

public fun grantPermissions(activity: Activity, permissions: Array<out String>, resultCode: Int) {
    val filtered = permissions.filter {
        !hasPermission(activity, it)
    }.toTypedArray()

    if (filtered.isNotEmpty()) {
        ActivityCompat.requestPermissions(activity, filtered, resultCode)
    }
}
