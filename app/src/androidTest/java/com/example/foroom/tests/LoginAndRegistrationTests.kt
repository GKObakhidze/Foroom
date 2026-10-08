
package com.example.foroom.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.foroom.TestSessionHelper
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    private val clearSessionRule = object : ExternalResource() {
        override fun before() {
            TestSessionHelper.clearSession()
        }
    }

    private val activityRule =
        ActivityScenarioRule(ForoomActivity::class.java)

    @get:Rule
    val rules: RuleChain = RuleChain
        .outerRule(clearSessionRule)
        .around(activityRule)

    @Test
    fun loginWithValidUsernameAndIncorrectPassword() {
        loginSteps.openLoginScreen()
        loginSteps.login("student", "WrongPassword123!")
        loginSteps.verifyPasswordError()
    }

    @Test
    fun loginWithNonexistentUsernameAndIncorrectPassword() {
        loginSteps.openLoginScreen()
        loginSteps.login(
            "nonexistent_user_987654",
            "WrongPassword123!"
        )
        loginSteps.verifyUsernameAndPasswordErrors()
    }

    @Test
    fun registerWithValidDataAndAvatar() {
        val uniqueUsername = "ani_${System.currentTimeMillis()}"
        val password = "Student123!"

        registrationSteps.openRegistrationScreen()
        registrationSteps.fillRegistrationForm(
            uniqueUsername,
            password
        )
        registrationSteps.selectAvatar()
        registrationSteps.submitRegistration()
        registrationSteps.verifySuccessfulRegistration()
    }
}
