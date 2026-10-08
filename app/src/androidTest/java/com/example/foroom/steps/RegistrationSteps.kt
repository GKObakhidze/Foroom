package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.RegistrationPage

object RegistrationSteps {
    fun checkRegistrationScreen() {
        RegistrationPage.checkDisplayed()
    }

    fun register(userName: String, password: String) {
        RegistrationPage.enterUserName(userName)
        RegistrationPage.enterPassword(password)
        RegistrationPage.enterRepeatPassword(password)
        RegistrationPage.selectAvatar(1)
        RegistrationPage.tapSignUp()
    }

    fun checkHomeScreen() {
        onView(withId(R.id.navBar)).waitUntilVisible(20)
    }
}
