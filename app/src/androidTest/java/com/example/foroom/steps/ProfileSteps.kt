package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ProfilePage
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText

class ProfileSteps {
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun goToProfile() {
        profilePage.homeNavigationProfile.perform(click())
    }

    fun clickChangePassword() {
        profilePage.changePasswordItem.perform(click())
    }

    fun changePassword(newPassword: String) {
        changePasswordPage.passwordInput.perform(typeText(newPassword), closeSoftKeyboard())
        changePasswordPage.repeatPasswordInput.perform(typeText(newPassword), closeSoftKeyboard())
        changePasswordPage.actionButton.perform(click())
    }
    fun clickChangeLanguageItem() {
        profilePage.changeLanguageItem.perform(click())
    }

    fun chooseGeorgian() {
        changeLanguagePage.languageButtonGeo.perform(click())
    }

    fun chooseEnglish() {
        changeLanguagePage.languageButtonEng.perform(click())
    }

    fun verifyLabelIsGeorgian() {
        profilePage.signOutItem.check(matches(hasDescendant(withText("გამოსვლა"))))
    }

    fun verifyLabelIsEnglish() {
        profilePage.signOutItem.check(matches(hasDescendant(withText("Sign Out"))))
    }
}