// MyLMSApp/app/src/main/java/com/example/lms/workers/NotificationWorker.kt
package com.example.lms.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.lms.R

class NotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        // In production, you would check the last time the app was opened (using SharedPreferences or a DB)
        sendNotification("We miss you!", "Please come back and continue your learning journey!")
        return Result.success()
    }

    private fun sendNotification(title: String, msg: String) {
        val nm = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "lms_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "LMS Notifications", NotificationManager.IMPORTANCE_DEFAULT)
            nm.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(msg)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        nm.notify(1001, notification)
    }
}
