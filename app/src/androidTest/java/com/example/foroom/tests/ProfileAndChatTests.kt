package com.example.foroom.tests
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.steps.RegistrationSteps
import com.example.foroom.tests.ProfileAndChatTests.Companion.TEST_PASS
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    companion object {
        private var isUserRegistered = false
        private const val TEST_USER = "QA_Auto_User123"
        private var TEST_PASS = "Aa123123@"
        private var currentPassword = "Aa123123@@!"
    }

    @Before
    fun setUp() {
        if (!isUserRegistered) {
            loginSteps.clickSignUp()

            registrationSteps.enterUsername(TEST_USER)
            registrationSteps.enterPassword(TEST_PASS)
            registrationSteps.enterRepeatPassword(TEST_PASS)
            registrationSteps.selectAvatarFromList()
            registrationSteps.clickSignUp()
            isUserRegistered = true
        } else {
            loginSteps.enterUsername(TEST_USER)
            loginSteps.enterPassword(TEST_PASS)
            loginSteps.clickLogin()
        }
    }


    @Test
    fun testProfilePasswordChange() {
        loginSteps.verifyHomeScreenIsDisplayed()
        profileSteps.goToProfile()
        profileSteps.clickChangePassword()

        val newPassword = "NewValidPassword123!"
        profileSteps.changePassword(newPassword)

        loginSteps.verifyLoginScreenIsDisplayed()
        loginSteps.enterUsername(TEST_USER)
        loginSteps.enterPassword(newPassword)
        loginSteps.clickLogin()

        loginSteps.verifyHomeScreenIsDisplayed()
        currentPassword = newPassword
    }

    @Test
    fun testProfileLanguageChange() {
        loginSteps.verifyHomeScreenIsDisplayed()
        profileSteps.goToProfile()

        profileSteps.clickChangeLanguageItem()
        profileSteps.chooseGeorgian()
        profileSteps.verifyLabelIsGeorgian()

        profileSteps.clickChangeLanguageItem()
        profileSteps.chooseEnglish()
        profileSteps.verifyLabelIsEnglish()

        profileSteps.clickChangeLanguageItem()
        profileSteps.chooseGeorgian()
        profileSteps.verifyLabelIsGeorgian()
    }

    @Test
    fun testCreateAndFindChat() {
        loginSteps.verifyHomeScreenIsDisplayed()
        chatSteps.openCreateChat()

        val timestamp = System.currentTimeMillis()
        val chatName = "Ana Morbedadze_$timestamp"
        chatSteps.enterChatName(chatName)

        chatSteps.selectChatImage()
        chatSteps.clickCreateChat()
        chatSteps.verifyChatScreenDisplaysName(chatName)
        chatSteps.closeChat()
        chatSteps.searchAndVerifyChat(chatName)
    }
}