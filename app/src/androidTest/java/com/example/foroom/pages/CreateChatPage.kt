package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import com.alternator.foroom.R as AppR
import com.example.design_system.R as DesignR

class CreateChatPage {
    val chatNameInput = onView(
        allOf(
            withId(DesignR.id.inputEditText),
            isDescendantOfA(withId(AppR.id.chatNameInput))
        )
    )
    val chatImageChooser = onView(withId(AppR.id.chatImageChooser))
    val createChatButton = onView(withId(AppR.id.createChatButton))
    val closeButton = onView(withId(AppR.id.closeButton))
}