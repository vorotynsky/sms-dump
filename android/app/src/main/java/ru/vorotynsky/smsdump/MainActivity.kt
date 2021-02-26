package ru.vorotynsky.smsdump

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editPhone: EditText = findViewById(R.id.main_editPhone)
        val updateButton: Button = findViewById(R.id.main_count)
        val resultText: TextView = findViewById(R.id.main_result)

        requestPermission()

        updateButton.setOnClickListener {
            val phone = editPhone.text.toString()
            resultText.text = "${calculateMessages(phone)} message(s) for $phone"
        }
    }

    private fun calculateMessages(phone: String): Int {
        val uri = Uri.parse("content://sms/inbox")

        val phoneArg = phone.filter { it.isDigit() }
        val cursor = contentResolver.query(uri, null, "address=?", arrayOf(phoneArg), null)

        return cursor?.use { it.count } ?: 0
    }

    private fun requestPermission() {
        PermissionManager.grantPermissions(this, arrayOf(Manifest.permission.READ_SMS), 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            PermissionManager.logPermissionGrantResult(grantResults, permissions)
        }
    }
}
