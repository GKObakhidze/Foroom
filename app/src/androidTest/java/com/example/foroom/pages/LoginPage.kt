package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object LoginPage {
    private val userNameInput = withId(R.id.userNameInput)
    private val passwordInput = withId(R.id.passwordInput)
    private val logInButton = withId(R.id.logInButton)
    private val signUpButton = withId(R.id.signUpButton)

    private val userNameField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    private val passwordField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    private val userNameError = allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(userNameInput))
    private val passwordError = allOf(withId(DesignR.id.descriptionTextView), isDescendantOfA(passwordInput))

    fun checkDisplayed() {
        onView(logInButton).waitUntilVisible(10)
    }

    fun enterUserName(value: String) {
        onView(userNameField).perform(replaceText(value), closeSoftKeyboard())
    }

    fun enterPassword(value: String) {
        onView(passwordField).perform(replaceText(value), closeSoftKeyboard())
    }

    fun tapLogIn() {
        onView(logInButton).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }

    fun checkUserNameError() {
        onView(userNameError).waitUntilVisible(10).check(matches(isDisplayed()))
    }

    fun checkPasswordError() {
        onView(passwordError).waitUntilVisible(10).check(matches(isDisplayed()))
    }
}
