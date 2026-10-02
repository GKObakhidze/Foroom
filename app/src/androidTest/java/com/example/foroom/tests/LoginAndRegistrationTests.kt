package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginAndRegistrationTests {
    @Before
    fun setUp() {
        loginSteps.ensureLoginScreenDisplayed()
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun loginWithValidUsernameAndInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps.login(
            username = "ninaa",
            password = "aa1234"
        )

        loginSteps.verifyPasswordErrorDisplayed()
    }

    @Test
    fun loginWithInvalidUsernameAndInvalidPassword() {
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps.login(
            username = "ninaaa",
            password = "aa1234"
        )

        loginSteps.verifyUsernameErrorDisplayed()
        loginSteps.verifyPasswordErrorDisplayed()
    }

    @Test
    fun successfulRegistration() {
        val uniqueUsername = "user_${System.currentTimeMillis()}"
        val password = "aaa123"

        loginSteps.verifyLoginScreenDisplayed()
        loginSteps.goToRegistration()

        registrationSteps.verifyRegistrationScreenDisplayed()

        registrationSteps.register(
            username = uniqueUsername,
            password = password
        )

        registrationSteps.verifyHomeScreenDisplayed()
    }
}