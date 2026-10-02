package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import com.example.foroom.utils.ClearSessionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule(order = 0)
    val clearSessionRule = ClearSessionRule()

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun validUsernameAndInvalidPasswordShowsPasswordError() {
        loginSteps
            .verifyLoginScreenIsDisplayed()
            .enterUserName(Constants.VALID_USERNAME)
            .enterPassword(Constants.INCORRECT_PASSWORD)
            .tapLogIn()
            .verifyPasswordErrorIsDisplayed()
    }

    @Test
    fun invalidUsernameAndInvalidPasswordShowsBothErrors() {
        loginSteps
            .verifyLoginScreenIsDisplayed()
            .enterUserName(Constants.NON_EXISTING_USERNAME)
            .enterPassword(Constants.INCORRECT_PASSWORD)
            .tapLogIn()
            .verifyUserNameErrorIsDisplayed()
            .verifyPasswordErrorIsDisplayed()
    }

    @Test
    fun successfulRegistrationOpensHomeScreen() {
        val uniqueUserName = Constants.USERNAME_PREFIX + System.currentTimeMillis()

        loginSteps
            .verifyLoginScreenIsDisplayed()
            .tapSignUp()

        registrationSteps
            .verifyRegistrationScreenIsDisplayed()
            .enterUserName(uniqueUserName)
            .enterPassword(Constants.REGISTRATION_PASSWORD)
            .enterRepeatPassword(Constants.REGISTRATION_PASSWORD)
            .selectAvatar(Constants.AVATAR_INDEX)
            .tapSignUp()
            .verifyHomeScreenIsDisplayed()
    }
}