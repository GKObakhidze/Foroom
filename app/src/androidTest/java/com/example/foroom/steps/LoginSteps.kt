package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.LoginPage

class LoginSteps(private val page: LoginPage = LoginPage()) {
    fun loginScreenShown() = waitUntil { page.logInButton().check(matches(isDisplayed())) }

    fun login(user: String, pass: String) {
        page.typeUsername(user)
        page.typePassword(pass)
        page.tapLogIn()
    }

    fun usernameErrorShown() = waitUntil { page.usernameError().check(matches(isDisplayed())) }
    fun passwordErrorShown() = waitUntil { page.passwordError().check(matches(isDisplayed())) }
}