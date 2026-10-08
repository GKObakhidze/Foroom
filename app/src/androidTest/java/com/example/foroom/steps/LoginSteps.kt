package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.constants.TestData
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage
import org.hamcrest.Matcher

object LoginSteps {
    fun checkLoginScreen() {
        onView(LoginPage.logInButton).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun ensureLoggedOut() {
        val deadline = System.currentTimeMillis() + TestData.LONG_TIMEOUT_SEC * 1000
        while (System.currentTimeMillis() < deadline) {
            if (isDisplayed(LoginPage.logInButton)) return
            if (isDisplayed(LoginPage.navBar)) {
                onView(ProfilePage.homeNavigationProfile).perform(click())
                onView(ProfilePage.signOutItem).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).perform(click())
            }
            Thread.sleep(TestData.POLL_INTERVAL_MS)
        }
        checkLoginScreen()
    }

    fun checkHomeScreen() {
        onView(LoginPage.navBar).waitUntilVisible(TestData.LONG_TIMEOUT_SEC)
    }

    fun login(userName: String, password: String) {
        onView(LoginPage.userNameField).perform(replaceText(userName), closeSoftKeyboard())
        onView(LoginPage.passwordField).perform(replaceText(password), closeSoftKeyboard())
        onView(LoginPage.logInButton).perform(click())
    }

    fun openRegistration() {
        onView(LoginPage.signUpButton).perform(click())
    }

    fun checkPasswordError() {
        onView(LoginPage.passwordError).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).check(matches(isDisplayed()))
    }

    fun checkUserNameError() {
        onView(LoginPage.userNameError).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).check(matches(isDisplayed()))
    }

    private fun isDisplayed(matcher: Matcher<View>): Boolean = try {
        onView(matcher).check(matches(isDisplayed()))
        true
    } catch (_: Exception) {
        false
    }
}
