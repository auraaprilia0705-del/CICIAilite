package com.example.ciciailite.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ciciailite.R

class ChatAdapter(private val chatList: List<String>) : BaseAdapter() {
    override fun getCount(): Int = chatList.size
    override fun getItem(position: Int): Any = chatList[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val message = chatList[position]
        val inflater = LayoutInflater.from(parent?.context)

        // Cek apakah pesan dari User atau AI
        val view: View = if (message.startsWith("User:")) {
            inflater.inflate(R.layout.item_chat_user, parent, false).apply {
                findViewById<TextView>(R.id.tvMessageUser).text = message.replace("User: ", "")
            }
        } else {
            inflater.inflate(R.layout.item_chat_ai, parent, false).apply {
                findViewById<TextView>(R.id.tvMessageAi).text = message.replace("Cici AI: ", "")
            }
        }
        return view
    }
}