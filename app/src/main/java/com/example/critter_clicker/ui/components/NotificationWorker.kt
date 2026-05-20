package com.example.critter_clicker.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.critter_clicker.R



class NotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        val builder =
            NotificationCompat.Builder(
                applicationContext,
                "critter_channel"
            )
                .setSmallIcon(R.drawable.monkey)
                .setContentTitle("Critter Clicker")
                .setContentText("Your pets are getting hungry!")
                .setPriority(
                    NotificationCompat.PRIORITY_DEFAULT
                )

        if (
            ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return Result.failure()
        }

        NotificationManagerCompat
            .from(applicationContext)
            .notify(1, builder.build())

        return Result.success()
    }
}