package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import com.example.foroom.pages.LoginPage

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
    val usernameError = onView(
        allOf(
            withId(DesignR.id.descriptionTextView),
            isDescendantOfA(withId(AppR.id.userNameInput))
        )
    )

    val passwordError = onView(
        allOf(
            withId(DesignR.id.descriptionTextView),
            isDescendantOfA(withId(AppR.id.passwordInput))
        )
    )
}