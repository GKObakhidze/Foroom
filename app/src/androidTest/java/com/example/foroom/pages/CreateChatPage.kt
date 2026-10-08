package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object CreateChatPage {
    val homeNavigationCreateChat = withId(R.id.homeNavigationCreateChat)
    val chatNameInput = withId(R.id.chatNameInput)
    val chatImageChooser = withId(R.id.chatImageChooser)
    val createChatButton = withId(R.id.createChatButton)
    val closeButton = withId(R.id.closeButton)
    val chatHeaderView = withId(R.id.chatHeaderView)

    val chatNameField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))

    fun chatTitle(name: String): Matcher<View> =
        allOf(withId(DesignR.id.chatNameTextView), withText(name), isDescendantOfA(chatHeaderView), isDisplayed())
}