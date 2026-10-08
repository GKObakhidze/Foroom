package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.constants.TestData
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

object ProfileSteps {
    fun openProfile() {
        onView(ProfilePage.homeNavigationProfile).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).perform(click())
        onView(ProfilePage.changePasswordItem).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun changePassword(newPassword: String) {
        onView(ProfilePage.changePasswordItem).perform(click())
        onView(ChangePasswordPage.passwordField).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
            .perform(replaceText(newPassword), closeSoftKeyboard())
        onView(ChangePasswordPage.repeatPasswordField).perform(replaceText(newPassword), closeSoftKeyboard())
        onView(ChangePasswordPage.actionButton).perform(click())
    }

    fun selectGeorgian() {
        openLanguageSelector()
        onView(ChangeLanguagePage.languageButtonGeo).perform(click())
    }

    fun selectEnglish() {
        openLanguageSelector()
        onView(ChangeLanguagePage.languageButtonEng).perform(click())
    }

    fun checkGeorgianLabels() {
        onView(ProfilePage.changeLanguageLabelGeorgian).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
        onView(ProfilePage.signOutLabelGeorgian).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    fun checkEnglishLabels() {
        onView(ProfilePage.changeLanguageLabelEnglish).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
        onView(ProfilePage.signOutLabelEnglish).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }

    private fun openLanguageSelector() {
        onView(ProfilePage.changeLanguageItem).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC).perform(click())
        onView(ChangeLanguagePage.languageButtonGeo).waitUntilVisible(TestData.DEFAULT_TIMEOUT_SEC)
    }
}