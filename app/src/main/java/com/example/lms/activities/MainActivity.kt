// MyLMSApp/app/src/main/java/com/example/lms/activities/MainActivity.kt
package com.example.lms.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.models.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        // Role-based routing (no UI for MainActivity itself)
        val user = intent.getSerializableExtra("user") as? User
        if (user != null) {
            if (user.role == "admin")
                startActivity(Intent(this, AdminDashboardActivity::class.java).apply {
                    putExtra("user", user)
                })
            else
                startActivity(Intent(this, UserDashboardActivity::class.java).apply {
                    putExtra("user", user)
                })
            finish()
        } else {
            // If no user is provided, go back to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
