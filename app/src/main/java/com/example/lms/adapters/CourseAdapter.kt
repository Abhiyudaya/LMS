package com.example.lms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lms.databinding.ItemCourseBinding
import com.example.lms.models.Course

class CourseAdapter(private var courses: MutableList<Course> = mutableListOf()) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.binding.tvCourseTitle.text = course.title
        holder.binding.tvCourseCategory.text = course.category
        holder.binding.tvCourseSummary.text = course.summary
        holder.binding.tvCourseDuration.text = "Duration: ${course.duration}"
    }


    override fun getItemCount(): Int = courses.size

    fun setCourses(newCourses: List<Course>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }
}
