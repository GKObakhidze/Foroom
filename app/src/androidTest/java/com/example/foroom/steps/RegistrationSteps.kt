
package com.example.foroom.steps

import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    private val loginPage = LoginPage()
    private val registrationPage = RegistrationPage()

    fun openRegistrationScreen() {
        loginPage.verifyLoginScreen()
        loginPage.clickSignUp()
        registrationPage.verifyRegistrationScreen()
    }

    fun fillRegistrationForm(
        username: String,
        password: String
    ) {
        registrationPage.enterUsername(username)
        registrationPage.enterPassword(password)
        registrationPage.repeatPassword(password)
    }

    fun selectAvatar() {
        registrationPage.selectAvatar()
    }

    fun submitRegistration() {
        registrationPage.clickSignUp()
    }

    fun verifySuccessfulRegistration() {
        registrationPage.verifyHomeScreen()
    }
}
