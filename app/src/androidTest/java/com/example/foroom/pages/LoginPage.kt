package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import com.alternator.foroom.R as AppR
import com.example.design_system.R as DesignR

class LoginPage {

    val usernameInput = onView(
        allOf(
            withId(DesignR.id.inputEditText),
            isDescendantOfA(withId(AppR.id.userNameInput))
        )
    )

    val passwordInput = onView(
        allOf(
            withId(DesignR.id.inputEditText),
            isDescendantOfA(withId(AppR.id.passwordInput))
        )
    )

    val loginButton = onView(withId(AppR.id.logInButton))

    val signUpButton = onView(withId(AppR.id.signUpButton))

    val usernameError = onView(
        allOf(
            withId(DesignR.id.descriptionTextView),
            isDescendantOfA(withId(AppR.id.userNameInput))
        )
    )

    val passwordError = onView(
        allOf(
            withId(DesignR.id.descriptionTextView),
            isDescendantOfA(withId(AppR.id.passwordInput))
        )
    )
}