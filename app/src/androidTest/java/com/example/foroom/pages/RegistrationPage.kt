package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.Helper.waitUntilVisible
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object RegistrationPage {
    private val userNameInput = withId(R.id.userNameInput)
    private val passwordInput = withId(R.id.passwordInput)
    private val repeatPasswordInput = withId(R.id.repeatPasswordInput)
    private val listView = withId(R.id.listView)
    private val signUpButton = withId(R.id.signUpButton)

    private val userNameField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    private val passwordField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    private val repeatPasswordField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))

    fun checkDisplayed() {
        onView(signUpButton).waitUntilVisible(10)
    }

    fun enterUserName(value: String) {
        onView(userNameField).perform(scrollTo(), replaceText(value), closeSoftKeyboard())
    }

    fun enterPassword(value: String) {
        onView(passwordField).perform(scrollTo(), replaceText(value), closeSoftKeyboard())
    }

    fun enterRepeatPassword(value: String) {
        onView(repeatPasswordField).perform(scrollTo(), replaceText(value), closeSoftKeyboard())
    }

    fun selectAvatar(index: Int) {
        val interaction = onView(listView)
        interaction.waitUntilVisible(10)
        interaction.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> = isAssignableFrom(ImageChooserListView::class.java)

            override fun getDescription() = "select avatar"

            override fun perform(uiController: UiController, view: View) {
                val list = view as ImageChooserListView
                var tries = 0
                while (!list.isChoosingEnabled && tries++ < 100) uiController.loopMainThreadForAtLeast(100)
                list.selectImageAt(index)
            }
        })
    }

    fun tapSignUp() {
        onView(signUpButton).perform(scrollTo(), click())
    }
}
