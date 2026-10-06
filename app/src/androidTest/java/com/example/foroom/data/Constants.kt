package com.example.foroom.data

object Constants {
    const val EXISTING_USERNAME = "student"
    const val NON_EXISTENT_USER_PREFIX = "no_suchuser"
    const val NEW_USER_PREFIX = "user_"

    const val WRONG_PASSWORD = "Incorrect#987"
    const val VALID_PASSWORD = "Student123!"

    const val AVATAR_POSITION = 0
    const val HOME_SCREEN_TIMEOUT_MS = 15_000L

    fun uniqueUsername(prefix: String) = "$prefix${System.currentTimeMillis()}"
}