package com.example.foroom.pages
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import com.alternator.foroom.R as AppR
import com.example.design_system.R as DesignR

class ChangePasswordPage {
    val passwordInput = onView(
        allOf(
            withId(DesignR.id.inputEditText),
            isDescendantOfA(withId(AppR.id.passwordInput))
        )
    )

    val repeatPasswordInput = onView(
        allOf(
            withId(DesignR.id.inputEditText),
            isDescendantOfA(withId(AppR.id.repeatPasswordInput))
        )
    )

    val actionButton = onView(withId(AppR.id.actionButton))
}