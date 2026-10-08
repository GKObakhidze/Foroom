package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.HomePage
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps(
    private val login: LoginPage = LoginPage(),
    private val page: RegistrationPage = RegistrationPage(),
    private val home: HomePage = HomePage()
) {
    fun openRegistration() {
        login.tapSignUp()
        registrationScreenShown()
    }

    fun registrationScreenShown() = waitUntil { page.repeatPasswordInput().check(matches(isDisplayed())) }

    fun register(user: String, pass: String) {
        page.typeUsername(user)
        page.typePassword(pass)
        page.typeRepeat(pass)
        waitUntil { page.tapAvatar() }
        page.tapSignUp()
    }

    fun homeShown() = waitUntil(15_000) { home.navBar().check(matches(isDisplayed())) }
}