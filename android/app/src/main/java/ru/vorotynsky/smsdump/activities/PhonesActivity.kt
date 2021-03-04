package ru.vorotynsky.smsdump.activities

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import ru.vorotynsky.smsdump.R
import ru.vorotynsky.smsdump.SMSHelper
import ru.vorotynsky.smsdump.adapters.PhonesAdapter

class PhonesActivity : AppCompatActivity() {
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phones)

        cursor = SMSHelper(this).getPhones()!!

        val list: RecyclerView = findViewById(R.id.phones_list)
        list.adapter = PhonesAdapter(cursor, this)
    }

    override fun onStop() {
        super.onStop()
        cursor.close()
    }
}
