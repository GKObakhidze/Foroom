package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import com.example.foroom.Helper.selectImageAt
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.constants.TestData
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

object ChatSteps {
    fun uniqueChatName(): String =
        "${TestData.CHAT_OWNER_FULL_NAME}${TestData.CHAT_NAME_SEPARATOR}${System.currentTimeMillis()}"

    fun openCreateChat() {
        onView(CreateChatPage.homeNavigationCreateChat).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).perform(click())
        onView(CreateChatPage.chatNameInput).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun enterChatName(name: String) {
        onView(CreateChatPage.chatNameField).perform(replaceText(name), closeSoftKeyboard())
    }

    fun selectChatImage() {
        onView(CreateChatPage.chatImageChooser).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
            .perform(selectImageAt(TestData.CHAT_IMAGE_INDEX))
    }

    fun tapCreateChat() {
        onView(CreateChatPage.createChatButton).perform(click())
    }

    fun checkCreatedChat(name: String) {
        onView(CreateChatPage.chatTitle(name)).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun closeChat() {
        onView(CreateChatPage.closeButton).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).perform(click())
        onView(ChatsPage.searchChatInput).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun searchChat(name: String) {
        onView(ChatsPage.searchField).perform(replaceText(name), closeSoftKeyboard())
    }

    fun checkChatInList(name: String) {
        onView(ChatsPage.chatCard(name)).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }
}