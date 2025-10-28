package com.android.application.cellularusagedemoapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

object NotificationHelper {
    private const val CHANNEL_ID = "cellular_demo_channel"


    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel =
            NotificationChannel(CHANNEL_ID, "Cellular Demo", NotificationManager.IMPORTANCE_DEFAULT)
        nm.createNotificationChannel(channel)
    }


    fun notifyLowBalance(context: Context, title: String, body: String) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val n = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()
        nm.notify(1, n)
    }
}