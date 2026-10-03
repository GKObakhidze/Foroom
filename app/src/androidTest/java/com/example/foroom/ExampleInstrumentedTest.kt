package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {


    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun successfulRegistrationTest() {
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps.clickSignUp()
        registrationSteps.verifyRegistrationScreenDisplayed()

        val uniqueUsername = "TestUser_" + System.currentTimeMillis()
        registrationSteps.enterUsername(uniqueUsername)

        val validPassword = "StrongPassword123!"
        registrationSteps.enterPassword(validPassword)
        registrationSteps.enterRepeatPassword(validPassword)

        registrationSteps.selectAvatarFromList()

        registrationSteps.clickSignUp()

        registrationSteps.verifyHomeScreenDisplayed()
    }
