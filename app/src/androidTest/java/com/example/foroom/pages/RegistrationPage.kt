package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserListView
import org.hamcrest.Matchers.allOf

class RegistrationPage {

    val usernameInput = allOf(
        withId(com.example.design_system.R.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    val passwordInput = allOf(
        withId(com.example.design_system.R.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    val repeatPasswordInput = allOf(
        withId(com.example.design_system.R.id.inputEditText),
        isDescendantOfA(withId(R.id.repeatPasswordInput))
    )

    val avatarList = withId(R.id.listView)

    val signUpButton = withId(R.id.signUpButton)

    fun enterUsername(username: String) {
        onView(usernameInput).perform(replaceText(username))
    }

    fun enterPassword(password: String) {
        onView(passwordInput).perform(replaceText(password))
    }

    fun enterRepeatPassword(password: String) {
        onView(repeatPasswordInput).perform(replaceText(password))
    }

    fun selectAvatar() {
        onView(avatarList).perform(object : ViewAction {

            override fun getConstraints() = isDisplayed()

            override fun getDescription() = "Select avatar"

            override fun perform(uiController: UiController, view: View) {
                (view as ImageChooserListView).selectImageAt(1)
            }
        })
    }

    fun clickSignUpButton() {
        onView(signUpButton).perform(click())
    }
    val homeNavBar = withId(R.id.navBar)
}