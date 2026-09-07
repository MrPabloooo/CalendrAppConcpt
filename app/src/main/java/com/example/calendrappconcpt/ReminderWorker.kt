package com.example.calendrappconcpt

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Duration
import java.util.concurrent.TimeUnit
class ReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        val hour = inputData.getInt("hour", -1)
        val message = inputData.getString("message") ?: ""
        val streak = StreakDataStore
            .getStreak(applicationContext)
            .first()


        if (hour == -1 || message.isEmpty()) {
            return Result.failure()
        }

        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.CalendarItemDao()

        val today = LocalDate.now().toString()

        val notes = dao.getNoteForDay(today).first()

        // Jeżeli dzisiaj jest już notatka → nic nie pokazuj
        if (notes.isEmpty()) {
            if (streak > 0) {
                sendNotification(
                    "$message 🔥$streak"
                )
            }
            else
            {
                sendNotification(message)
            }
        }

        // Zaplanuj następne przypomnienie
        ReminderManager.schedule(
            applicationContext,
            hour,
            0,
            message
        )

        return Result.success()
    }

    private fun sendNotification(message: String) {

        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        ).apply {
            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        val notificationManager =
            applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        val notification =
            NotificationCompat.Builder(
                applicationContext,
                "note_reminder"
            )
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Calendar")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }
}