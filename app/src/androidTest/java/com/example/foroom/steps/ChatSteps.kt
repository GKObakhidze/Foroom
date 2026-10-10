package com.example.foroom.steps
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.pages.ProfilePage
import com.example.design_system.R as DesignR
import org.hamcrest.Matchers.allOf

class ChatSteps {
    private val createChatPage = CreateChatPage()
    private val profilePage = ProfilePage()

    fun openCreateChat() {
        profilePage.homeNavigationCreateChat.perform(click())
    }

    fun enterChatName(chatName: String) {
        createChatPage.chatNameInput.perform(typeText(chatName), closeSoftKeyboard())
    }

    fun selectChatImage() {
        createChatPage.chatImageChooser.perform(click())
    }

    fun clickCreateChat() {
        createChatPage.createChatButton.perform(click())
    }

    fun closeChat() {
        createChatPage.closeButton.perform(click())
    }

    fun verifyChatScreenDisplaysName(expectedName: String) {
        onView(
            allOf(
                withId(DesignR.id.chatTitleTextView),
                withText(expectedName)
            )
        ).check(matches(isDisplayed()))
    }

    fun searchAndVerifyChat(chatName: String) {
        profilePage.searchChatInput.perform(typeText(chatName), closeSoftKeyboard())
        profilePage.chatsRecyclerView.check(matches(hasDescendant(withText(chatName))))
    }
}