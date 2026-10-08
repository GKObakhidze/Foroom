package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants.EXISTING_USER
import com.example.foroom.data.Constants.VALID_PASSWORD
import com.example.foroom.data.Constants.WRONG_PASSWORD
import com.example.foroom.data.Constants.uniqueUsername
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {
    @get:Rule
    val rule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun validUsernameInvalidPassword() {
        loginSteps.loginScreenShown()
        loginSteps.login(EXISTING_USER, WRONG_PASSWORD)
        loginSteps.passwordErrorShown()
    }

    @Test
    fun invalidUsernameInvalidPassword() {
        loginSteps.loginScreenShown()
        loginSteps.login(uniqueUsername(), WRONG_PASSWORD)
        loginSteps.usernameErrorShown()
        loginSteps.passwordErrorShown()
    }

    @Test
    fun successfulRegistration() {
        loginSteps.loginScreenShown()
        registrationSteps.openRegistration()
        registrationSteps.register(uniqueUsername(), VALID_PASSWORD)
        registrationSteps.homeShown()
    }
}