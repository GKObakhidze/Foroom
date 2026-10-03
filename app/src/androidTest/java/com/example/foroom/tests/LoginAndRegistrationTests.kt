package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test

class LoginAndRegistrationTests {
    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @get:Rule
    val rule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun validUsernameInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(Constants.EXISTING_USERNAME, Constants.WRONG_PASSWORD)
        loginSteps.verifyPasswordError()
    }

    @Test
    fun invalidUsernameInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.loginWith(
            Constants.uniqueUsername(Constants.NON_EXISTENT_USER_PREFIX),
            Constants.WRONG_PASSWORD
        )
        loginSteps.verifyUsernameError()
        loginSteps.verifyPasswordError()
    }

    @Test
    fun successfulRegistration() {
        loginSteps.verifyLoginScreenDisplayed()
        registrationSteps.openRegistrationScreen()
        registrationSteps.registerNewUser(
            Constants.uniqueUsername(Constants.NEW_USER_PREFIX),
            Constants.VALID_PASSWORD
        )
        registrationSteps.verifyHomeScreenDisplayed()
    }
}