package com.example.foroom.pages

import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.constants.TestData
import org.hamcrest.Matchers.allOf

object ProfilePage {
    val homeNavigationProfile = withId(R.id.homeNavigationProfile)
    val changePasswordItem = withId(R.id.changePasswordItem)
    val changeLanguageItem = withId(R.id.changeLanguageItem)
    val signOutItem = withId(R.id.signOutItem)

    val changeLanguageLabelGeorgian =
        allOf(withText(TestData.LABEL_CHANGE_LANGUAGE_GEORGIAN), isDescendantOfA(changeLanguageItem))
    val signOutLabelGeorgian =
        allOf(withText(TestData.LABEL_SIGN_OUT_GEORGIAN), isDescendantOfA(signOutItem))
    val changeLanguageLabelEnglish =
        allOf(withText(TestData.LABEL_CHANGE_LANGUAGE_ENGLISH), isDescendantOfA(changeLanguageItem))
    val signOutLabelEnglish =
        allOf(withText(TestData.LABEL_SIGN_OUT_ENGLISH), isDescendantOfA(signOutItem))
}