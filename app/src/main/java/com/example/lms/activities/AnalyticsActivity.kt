package com.example.lms.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivityAnalyticsBinding

class AnalyticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalyticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set static/dummy analytics data – in a real app, replace these with live data
        binding.tvAverageScoreValue.text = "87%"
        binding.tvOverallAccuracyValue.text = "92%"
        binding.tvTotalUsersValue.text = "120"
        binding.tvRankedUsersValue.text = "John Doe, Jane Smith, Alex Roe, Sam Johnson"
        binding.tvQuestionAnalysisValue.text = """
            Q1: 95% correct (100 attempts)
            Q2: 88% correct (100 attempts)
            Q3: 92% correct (100 attempts)
            Q4: 85% correct (100 attempts)
        """.trimIndent()

        // Lottie animation auto-plays based on XML settings (app:lottie_autoPlay="true").
        // Optionally, control the animation:
        // binding.lottieAnalytics.playAnimation()
    }
}
