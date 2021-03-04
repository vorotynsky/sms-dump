package ru.vorotynsky.smsdump.activities

import android.Manifest
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ru.vorotynsky.smsdump.R
import ru.vorotynsky.smsdump.SMSHelper
import ru.vorotynsky.smsdump.adapters.PhonesAdapter
import ru.vorotynsky.smsdump.grantPermissions

class PhonesActivity : AppCompatActivity() {
    private lateinit var cursor: Cursor
    private lateinit var adapter: PhonesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phones)

        requestPermission()
        cursor = SMSHelper(this).getPhones()!!

        val list: RecyclerView = findViewById(R.id.phones_list)
        adapter = PhonesAdapter(cursor, this)
        list.adapter = adapter
    }

    private fun requestPermission() {
        grantPermissions(this, arrayOf(Manifest.permission.READ_SMS), 0)
    }

    override fun onStart() {
        super.onStart()
        cursor = SMSHelper(this).getPhones()!!
        adapter.resetCursor(cursor, false)
    }

    override fun onStop() {
        super.onStop()
        cursor.close()
    }
}
