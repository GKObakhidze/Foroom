package com.example.foroom.steps

import com.example.foroom.pages.LoginPage

object LoginSteps {
    fun checkLoginScreen() {
        LoginPage.checkDisplayed()
    }

    fun login(userName: String, password: String) {
        LoginPage.enterUserName(userName)
        LoginPage.enterPassword(password)
        LoginPage.tapLogIn()
    }

    fun openRegistration() {
        LoginPage.tapSignUp()
    }

    fun checkPasswordError() {
        LoginPage.checkPasswordError()
    }

    fun checkUserNameError() {
        LoginPage.checkUserNameError()
    }
}
