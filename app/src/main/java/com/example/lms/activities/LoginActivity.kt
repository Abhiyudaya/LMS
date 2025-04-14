// MyLMSApp/app/src/main/java/com/example/lms/activities/LoginActivity.kt
package com.example.lms.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.R
import com.example.lms.databinding.ActivityLoginBinding
import com.example.lms.models.User
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bgAnim = findViewById<ImageView>(R.id.bgAnim)
        val animation = bgAnim.drawable as? AnimationDrawable
        animation?.start()

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pwd = binding.etPwd.text.toString().trim()

            if (email.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // For demo purposes: decide role based on email. In production, load user details from Firestore.
                        val role = if (email.contains("admin", ignoreCase = true)) "admin" else "user"
                        val user = User(auth.currentUser?.uid ?: "", "Firebase User", email, role)
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.tvReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
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
