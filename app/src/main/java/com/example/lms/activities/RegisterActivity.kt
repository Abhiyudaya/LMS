// MyLMSApp/app/src/main/java/com/example/lms/activities/RegisterActivity.kt
package com.example.lms.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.R
import com.example.lms.databinding.ActivityRegisterBinding
import com.example.lms.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerAnim.playAnimation()

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pwd = binding.etPwd.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid ?: ""
                        // Assume role "user" for registered users.
                        val user = User(uid, name, email, "user")
                        firestore.collection("users")
                            .document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("user", user)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed to save user: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
