package ru.vorotynsky.smsdump.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.vorotynsky.smsdump.*

class CountActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phone = intent.extras?.get("phone").toString()

        requestPermission()
        val countMessages = SMSHelper(this).countMessages(phone)

        val textView: TextView = findViewById(R.id.count_phone)
        val countView: TextView = findViewById(R.id.count_count)

        textView.text = phone
        countView.text = "$countMessages messages"
    }

    private fun requestPermission() {
        grantPermissions(this, arrayOf(Manifest.permission.READ_SMS), 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            logPermissionGrantResult(grantResults, permissions)
        }
    }
}
