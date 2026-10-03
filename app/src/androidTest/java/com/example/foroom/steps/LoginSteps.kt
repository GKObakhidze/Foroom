package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntil
import com.example.foroom.pages.LoginPage

class LoginSteps(private val page: LoginPage = LoginPage()) {

    fun verifyLoginScreenDisplayed() = waitUntil {
        page.logInButton().check(matches(isDisplayed()))
    }

    fun loginWith(username: String, password: String) {
        page.usernameInput().perform(replaceText(username), closeSoftKeyboard())
        page.passwordInput().perform(replaceText(password), closeSoftKeyboard())
        page.logInButton().perform(click())
    }

    fun verifyUsernameError() = waitUntil { page.usernameError().check(matches(isDisplayed())) }
    fun verifyPasswordError() = waitUntil { page.passwordError().check(matches(isDisplayed())) }
}