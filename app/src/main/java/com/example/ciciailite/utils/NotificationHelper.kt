package com.example.ciciailite.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.ciciailite.R

class NotificationHelper(val context: Context) {
    private val CHANNEL_ID = "cici_channel"
    private val NOTIFY_ID = 1

    fun showNotification(message: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Buat Channel untuk Android Oreo ke atas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Cici AI Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Ikon sistem atau R.drawable.ic_notif
            .setContentTitle("Cici AI Lite")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        // Memanggil fungsi notify
        manager.notify(NOTIFY_ID, builder.build())
    }
}