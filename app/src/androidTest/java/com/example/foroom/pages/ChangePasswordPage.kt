package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ChangePasswordPage {
    val passwordInput = withId(R.id.passwordInput)
    val repeatPasswordInput = withId(R.id.repeatPasswordInput)
    val actionButton = withId(DesignR.id.actionButton)

    val passwordField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))
}