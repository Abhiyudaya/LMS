package com.example.lms.activities

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lms.databinding.ActivityCourseCreationBinding
import com.example.lms.models.Course
import com.google.firebase.firestore.FirebaseFirestore

class CourseCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseCreationBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        val contentTypes = listOf("Practice", "Test", "Reference Book", "Video Lesson")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, contentTypes)
        binding.spContentType.adapter = spinnerAdapter

        binding.btnInviteUser.setOnClickListener {
            animateButton(it)
            val inviteEmail = binding.etInviteEmail.text.toString().trim()
            if (inviteEmail.isEmpty()) {
                Toast.makeText(this, "Enter an email to invite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invitation sent to $inviteEmail", Toast.LENGTH_SHORT).show()
                binding.etInviteEmail.text.clear()
            }
        }

        binding.btnCreateCourse.setOnClickListener {
            animateButton(it)

            val title = binding.etCourseTitle.text.toString().trim()
            val summary = binding.etCourseSummary.text.toString().trim()
            val contentType = binding.spContentType.selectedItem.toString()
            val duration = binding.etCourseDuration.text.toString().trim()

            if (title.isEmpty() || summary.isEmpty() || duration.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val course = Course(
                title = title,
                category = contentType,
                summary = summary,
                duration = duration
            )

            firestore.collection("courses")
                .add(course)
                .addOnSuccessListener {
                    Toast.makeText(this, "Course created successfully", Toast.LENGTH_SHORT).show()
                    binding.etCourseTitle.text.clear()
                    binding.etCourseSummary.text.clear()
                    binding.etCourseDuration.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error creating course: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnManageContent.setOnClickListener {
            animateButton(it)
            Toast.makeText(this, "Manage Content tapped", Toast.LENGTH_SHORT).show()
        }
    }

    private fun animateButton(button: View) {
        val scaleX = ObjectAnimator.ofFloat(button, "scaleX", 0.9f, 1f)
        val scaleY = ObjectAnimator.ofFloat(button, "scaleY", 0.9f, 1f)
        scaleX.duration = 300
        scaleY.duration = 300
        scaleX.interpolator = BounceInterpolator()
        scaleY.interpolator = BounceInterpolator()
        scaleX.start()
        scaleY.start()
    }
}
