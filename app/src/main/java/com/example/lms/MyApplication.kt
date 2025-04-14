// MyLMSApp/app/src/main/java/com/example/lms/MyApplication.kt
package com.example.lms

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.lms.workers.NotificationWorker
import com.google.firebase.FirebaseApp
import java.util.concurrent.TimeUnit

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        val workRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java, 6, TimeUnit.HOURS
        ).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
