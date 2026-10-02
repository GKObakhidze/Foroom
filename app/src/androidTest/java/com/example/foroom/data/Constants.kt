package com.example.foroom.data

object Constants {
    val EXISTING_USER = "student"
    val WRONG_PASSWORD = "wrongPass123"
    val VALID_PASSWORD = "Student123!"

    fun uniqueUsername() = "user_${System.currentTimeMillis()}"
}