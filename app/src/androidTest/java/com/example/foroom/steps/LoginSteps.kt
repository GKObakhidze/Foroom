package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.LoginPage

class LoginSteps {
    private val loginPage = LoginPage()

    fun verifyLoginScreenIsDisplayed(): LoginSteps {
        onView(loginPage.logInButton).waitUntilVisible(10)
        return this
    }

    fun enterUserName(userName: String): LoginSteps {
        onView(loginPage.userNameField).input(userName)
        return this
    }

    fun enterPassword(password: String): LoginSteps {
        onView(loginPage.passwordField).input(password)
        return this
    }

    fun tapLogIn(): LoginSteps {
        onView(loginPage.logInButton).tap()
        return this
    }

    fun verifyPasswordErrorIsDisplayed(): LoginSteps {
        onView(loginPage.passwordError).waitUntilVisible(10)
        return this
    }

    fun verifyUserNameErrorIsDisplayed(): LoginSteps {
        onView(loginPage.userNameError).waitUntilVisible(10)
        return this
    }

    fun tapSignUp(): LoginSteps {
        onView(loginPage.signUpButton).tap()
        return this
    }
}