package com.example.calendrappconcpt

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

object ReminderManager {

    fun scheduleAll(context: Context) {

        schedule(
            context,
            7,
            0,
            "Gooood Morning!!! Don't forget about your daily note!"
        )


        schedule(
            context,
            12,
            0,
            "Hiiiiii, how is your day, maybe you should write a note?"
        )

        schedule(
            context,
            18,
            0,
            "Hellooo, how was your day, your daily note is still waiting!"
        )



        schedule(
            context,
            21,
            0,
            "Gooood Afternoon!!! Don't forget about your daily note!"
        )

        schedule(
            context,
            23,
            0,
            "!!!Last chance to keep your streak going!!!"
        )
    }

    fun schedule(
        context: Context,
        hour: Int,
        minute: Int,
        message: String,

    ) {

        val now = LocalDateTime.now()

        var target = now
            .withHour(hour)
            .withMinute(minute)
            .withSecond(0)
            .withNano(0)

        if (!target.isAfter(now)) {
            target = target.plusDays(1)
        }

        val delay =
            Duration.between(now, target)

        val request =
            OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(
                    delay.toMillis(),
                    TimeUnit.MILLISECONDS
                )
                .setInputData(
                    workDataOf(
                        "hour" to hour,
                        "message" to message
                    )
                )
                .build()

        WorkManager
            .getInstance(context)
            .enqueueUniqueWork(
                "reminder_${hour}_${minute}",
                ExistingWorkPolicy.REPLACE,
                request
            )
    }
}