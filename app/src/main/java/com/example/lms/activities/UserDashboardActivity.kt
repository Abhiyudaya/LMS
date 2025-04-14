// MyLMSApp/app/src/main/java/com/example/lms/activities/UserDashboardActivity.kt
package com.example.lms.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lms.R
import com.example.lms.adapters.CourseAdapter
import com.example.lms.databinding.ActivityUserDashboardBinding
import com.example.lms.models.Course
import com.example.lms.models.User
import com.google.firebase.firestore.FirebaseFirestore

class UserDashboardActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var binding : ActivityUserDashboardBinding
    private lateinit var adapter : CourseAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityUserDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        user = intent.getSerializableExtra("user") as User
        binding.tvWelcome.text = "${
            getString(R.string.welcome)
        }, ${user.name}!"

        // Display dummy score and ranking (replace with real values if available)
        binding.tvScore.text = "Score: 95"
        binding.tvRanking.text = "Ranking: Top 10"

        // Set up the RecyclerView to display FREE courses.
        adapter = CourseAdapter()
        binding.rvCourses.layoutManager = LinearLayoutManager(this)
        binding.rvCourses.adapter = adapter

        loadFreeCourses()
    }
    private fun loadFreeCourses() {
        firestore.collection("courses")
            .whereEqualTo("type", "FREE")
            .get()
            .addOnSuccessListener { snapshot ->
                val courses = mutableListOf<Course>()
                for (doc in snapshot.documents) {
                    doc.toObject(Course::class.java)?.let { courses.add(it) }
                }
                adapter.setCourses(courses)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load courses: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
