package com.example.lms.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivityAdminDashboardBinding
import com.example.lms.models.User

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var binding : ActivityAdminDashboardBinding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        user = intent.getSerializableExtra("user") as User

        binding.btnCreateCourse.setOnClickListener {
            startActivity(Intent(this, CourseCreationActivity::class.java))
        }
        binding.btnAnalytics.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val background = window.decorView.background
            if (background is AnimationDrawable) {
                background.start()
            }
        }
    }
}
