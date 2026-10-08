package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class LoginPage {
    private fun child(parent: Int, child: Int) =
        onView(allOf(withId(child), isDescendantOfA(withId(parent))))

    fun logInButton() = onView(withId(R.id.logInButton))
    fun usernameError() = child(R.id.userNameInput, DS.id.descriptionTextView)
    fun passwordError() = child(R.id.passwordInput, DS.id.descriptionTextView)

    fun typeUsername(v: String) = child(R.id.userNameInput, DS.id.inputEditText).perform(replaceText(v), closeSoftKeyboard())
    fun typePassword(v: String) = child(R.id.passwordInput, DS.id.inputEditText).perform(replaceText(v), closeSoftKeyboard())
    fun tapLogIn() = logInButton().perform(click())
    fun tapSignUp() = onView(withId(R.id.signUpButton)).perform(click())
}