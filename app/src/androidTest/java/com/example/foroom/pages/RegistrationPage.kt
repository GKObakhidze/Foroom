package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class RegistrationPage {
    private fun input(parent: Int) =
        onView(allOf(withId(DS.id.inputEditText), isDescendantOfA(withId(parent))))

    fun repeatPasswordInput() = onView(withId(R.id.repeatPasswordInput))

    fun typeUsername(v: String) = input(R.id.userNameInput).perform(replaceText(v), closeSoftKeyboard())
    fun typePassword(v: String) = input(R.id.passwordInput).perform(replaceText(v), closeSoftKeyboard())
    fun typeRepeat(v: String) = input(R.id.repeatPasswordInput).perform(replaceText(v), closeSoftKeyboard())
    fun tapAvatar(pos: Int = 0) =
        onView(allOf(withParent(withId(R.id.listView)), withParentIndex(pos))).perform(click())
    fun tapSignUp() = onView(withId(R.id.signUpButton)).perform(click())
}