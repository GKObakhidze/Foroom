package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.assertIsViewDisplayed
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    private val registrationPage = RegistrationPage()

    fun register(
        username: String,
        password: String
    ) {
        registrationPage.enterUsername(username)
        registrationPage.enterPassword(password)
        registrationPage.enterRepeatPassword(password)
        registrationPage.selectAvatar()
        registrationPage.clickSignUpButton()
    }

    fun verifyRegistrationScreenDisplayed() {
        onView(registrationPage.repeatPasswordInput)
            .check(matches(isDisplayed()))
    }
    fun verifyHomeScreenDisplayed() {
        registrationPage.homeNavBar.assertIsViewDisplayed()
    }
}