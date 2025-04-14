// MyLMSApp/app/src/main/java/com/example/lms/models/Course.kt
package com.example.lms.models

data class Course(
    val id: String = "",
    val title: String = "",
    val type: String = "FREE",  // e.g. FREE, PAID
    val category: String = "",
    val summary: String = "",
    val duration: String = ""   // New field added
)


data class Content(
    val id: String,
    val heading: String,
    val summary: String,
    val url: String,      // URL to video, pdf, doc, etc.
    val category: String  // e.g., practice, test, reference, lesson
)
