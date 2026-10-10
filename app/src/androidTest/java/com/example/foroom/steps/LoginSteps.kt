package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import com.example.foroom.pages.LoginPage
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.Espresso.onView
import com.alternator.foroom.R as AppR

class LoginSteps {
    private val loginPage = LoginPage()

    fun enterUsername(username: String) {
        loginPage.usernameInput.perform(typeText(username), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        loginPage.passwordInput.perform(typeText(password), closeSoftKeyboard())
    }

    fun clickLogin() {
        loginPage.loginButton.perform(click())
    }

    fun clickSignUp() {
        loginPage.signUpButton.perform(click())
    }

    fun verifyLoginScreenIsDisplayed() {
        loginPage.loginButton.check(matches(isDisplayed()))
    }

    fun verifyHomeScreenIsDisplayed() {
        onView(withId(AppR.id.navBar)).check(matches(isDisplayed()))
    }
}