package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.withIndex
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class RegistrationPage {
    val signUpButton: Matcher<View> = withId(R.id.signUpButton)
    val homeNavBar: Matcher<View> = withId(R.id.navBar)

    val userNameField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )
    val passwordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )
    val repeatPasswordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(withId(R.id.repeatPasswordInput))
    )

    fun avatarAt(index: Int): Matcher<View> = withIndex(
        allOf(
            isAssignableFrom(ImageChooserItemView::class.java),
            isDescendantOfA(withId(R.id.listView))
        ),
        index
    )
}