package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withParentIndex
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DS

class RegistrationPage {
    private fun inputChild(parent: Int) =
        onView(allOf(withId(DS.id.inputEditText), isDescendantOfA(withId(parent))))

    fun usernameInput() = inputChild(R.id.userNameInput)
    fun passwordInput() = inputChild(R.id.passwordInput)
    fun repeatPasswordInput() = inputChild(R.id.repeatPasswordInput)
    fun repeatPasswordContainer() = onView(withId(R.id.repeatPasswordInput))
    fun avatar(position: Int) =
        onView(allOf(withParent(withId(R.id.listView)), withParentIndex(position)))

    fun signUpButton() = onView(withId(R.id.signUpButton))
}