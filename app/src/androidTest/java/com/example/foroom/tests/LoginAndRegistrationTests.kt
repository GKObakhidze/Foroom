package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Test
    fun validUserNameAndInvalidPassword() {
        LoginSteps.checkLoginScreen()
        LoginSteps.login(EXISTING_USER, WRONG_PASSWORD)
        LoginSteps.checkPasswordError()
    }

    @Test
    fun invalidUserNameAndInvalidPassword() {
        LoginSteps.checkLoginScreen()
        LoginSteps.login("missing_${System.currentTimeMillis()}", WRONG_PASSWORD)
        LoginSteps.checkUserNameError()
        LoginSteps.checkPasswordError()
    }

    @Test
    fun successfulRegistration() {
        LoginSteps.checkLoginScreen()
        LoginSteps.openRegistration()
        RegistrationSteps.checkRegistrationScreen()
        RegistrationSteps.register("user_${System.currentTimeMillis()}", VALID_PASSWORD)
        RegistrationSteps.checkHomeScreen()
    }

    companion object {
        private const val EXISTING_USER = "test_user"
        private const val WRONG_PASSWORD = "wrong_password_123"
        private const val VALID_PASSWORD = "Password123"
    }
}
