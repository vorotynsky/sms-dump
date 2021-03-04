package ru.vorotynsky.smsdump.adapters

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.vorotynsky.smsdump.R
import ru.vorotynsky.smsdump.activities.CountActivity

class PhonesAdapter(private var cursor: Cursor, context: Context) : RecyclerView.Adapter<PhonesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.phones_name)
        private val card: CardView = itemView.findViewById<CardView>(R.id.phones_card).apply {
            setOnClickListener {
                val intent = Intent(context, CountActivity::class.java).apply {
                    putExtra("phone", textView.text)
                }
                context.startActivity(intent)
            }
        }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.phones_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cursor.count

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.textView.text = cursor.getString(0)
    }

    fun resetCursor(newCursor: Cursor, close: Boolean = false) {
        if (close)
            this.cursor.close()
        this.cursor = newCursor
    }
}
