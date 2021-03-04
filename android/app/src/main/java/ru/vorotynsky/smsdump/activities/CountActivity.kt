package ru.vorotynsky.smsdump.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
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

        val editPhone: EditText = findViewById(R.id.main_editPhone)
        val updateButton: Button = findViewById(R.id.main_count)
        val contactsButton: Button = findViewById(R.id.main_contacts)
        val resultText: TextView = findViewById(R.id.main_result)

        val smsProvider = SMSHelper(this)

        requestPermission()

        updateButton.setOnClickListener {
            val phone = editPhone.text.toString()
            resultText.text = "${smsProvider.countMessages(phone)} message(s) for $phone"
        }

        contactsButton.setOnClickListener {
            val intent = Intent(this, PhonesActivity::class.java)
            startActivity(intent)
        }
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
