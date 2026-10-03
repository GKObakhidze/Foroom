package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntil
import com.example.foroom.data.Constants
import com.example.foroom.pages.*

class RegistrationSteps(
    private val loginPage: LoginPage = LoginPage(),
    private val page: RegistrationPage = RegistrationPage()
) {

    fun openRegistrationScreen() {
        loginPage.signUpButton().perform(click())
        waitUntil { page.repeatPasswordContainer().check(matches(isDisplayed())) }
    }

    fun registerNewUser(username: String, password: String) {
        page.usernameInput().perform(replaceText(username), closeSoftKeyboard())
        page.passwordInput().perform(replaceText(password), closeSoftKeyboard())
        page.repeatPasswordInput().perform(replaceText(password), closeSoftKeyboard())
        waitUntil { page.avatar(Constants.AVATAR_POSITION).perform(click()) }
        page.signUpButton().perform(click())
    }

    fun verifyHomeScreenDisplayed() = waitUntil(Constants.HOME_SCREEN_TIMEOUT_MS) {
        onView(withId(R.id.navBar)).check(matches(isDisplayed()))
    }
}