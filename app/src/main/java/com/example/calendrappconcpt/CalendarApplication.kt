package com.example.calendrappconcpt

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class CalendarApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel(
            "note_reminder",
            "Przypomnienia",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val notificationManager =
            getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(channel)
    }
}