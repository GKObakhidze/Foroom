package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {
    private val registrationPage = RegistrationPage()

    fun verifyRegistrationScreenIsDisplayed(): RegistrationSteps {
        onView(registrationPage.repeatPasswordField).waitUntilVisible(10)
        return this
    }

    fun enterUserName(userName: String): RegistrationSteps {
        onView(registrationPage.userNameField).input(userName)
        return this
    }

    fun enterPassword(password: String): RegistrationSteps {
        onView(registrationPage.passwordField).input(password)
        return this
    }

    fun enterRepeatPassword(password: String): RegistrationSteps {
        onView(registrationPage.repeatPasswordField).input(password)
        return this
    }

    fun selectAvatar(index: Int): RegistrationSteps {
        onView(registrationPage.avatarAt(index)).perform(click())
        return this
    }

    fun tapSignUp(): RegistrationSteps {
        onView(registrationPage.signUpButton).tap()
        return this
    }

    fun verifyHomeScreenIsDisplayed(): RegistrationSteps {
        onView(registrationPage.homeNavBar).waitUntilVisible(10)
        return this
    }
}