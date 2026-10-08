package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.TestData
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    @Before
    fun openLogin() {
        LoginSteps.ensureLoggedOut()
    }

    @Test
    fun changePasswordAndLoginWithNewPassword() {
        LoginSteps.login(TestData.USER_NAME, TestData.PASSWORD)
        LoginSteps.checkHomeScreen()
        ProfileSteps.openProfile()
        ProfileSteps.changePassword(TestData.NEW_PASSWORD)
        LoginSteps.checkLoginScreen()
        LoginSteps.login(TestData.USER_NAME, TestData.NEW_PASSWORD)
        LoginSteps.checkHomeScreen()

        // restore original password so the test can be repeated
        ProfileSteps.openProfile()
        ProfileSteps.changePassword(TestData.PASSWORD)
        LoginSteps.checkLoginScreen()
    }

    @Test
    fun changeLanguageGeorgianToEnglishAndBack() {
        LoginSteps.login(TestData.USER_NAME, TestData.PASSWORD)
        LoginSteps.checkHomeScreen()
        ProfileSteps.openProfile()
        ProfileSteps.selectGeorgian()
        ProfileSteps.checkGeorgianLabels()
        ProfileSteps.selectEnglish()
        ProfileSteps.checkEnglishLabels()
        ProfileSteps.selectGeorgian()
        ProfileSteps.checkGeorgianLabels()
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = ChatSteps.uniqueChatName()

        LoginSteps.login(TestData.USER_NAME, TestData.PASSWORD)
        LoginSteps.checkHomeScreen()
        ChatSteps.openCreateChat()
        ChatSteps.enterChatName(chatName)
        ChatSteps.selectChatImage()
        ChatSteps.tapCreateChat()
        ChatSteps.checkCreatedChat(chatName)
        ChatSteps.closeChat()
        ChatSteps.searchChat(chatName)
        ChatSteps.checkChatInList(chatName)
    }
}