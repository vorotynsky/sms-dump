package ru.vorotynsky.smsdump

import android.content.ContextWrapper
import android.database.Cursor
import android.net.Uri

class SMSHelper(private val context: ContextWrapper) {
    private val uri = Uri.parse("content://sms/inbox")

    public fun countMessages(phone: String): Int {
        val phoneArg = phone.filter { it.isDigit() }
        val cursor = context.contentResolver.query(uri, null, "address=?", arrayOf(phoneArg), null)

        return cursor?.use { it.count } ?: 0
    }
}