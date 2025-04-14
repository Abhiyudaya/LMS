package com.example.lms.models

import java.io.Serializable

data class User(val id: String, val name: String, val email: String, val role: String) : Serializable
