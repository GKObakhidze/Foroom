package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R as AppR

class ProfilePage {
    val homeNavigationProfile = onView(withId(AppR.id.homeNavigationProfile))
    val changePasswordItem = onView(withId(AppR.id.changePasswordItem))
    val changeLanguageItem = onView(withId(AppR.id.changeLanguageItem))
    val signOutItem = onView(withId(AppR.id.signOutItem))
    val homeNavigationCreateChat = onView(withId(AppR.id.homeNavigationCreateChat))
    val searchChatInput = onView(withId(AppR.id.searchChatInput))
    val chatsRecyclerView = onView(withId(AppR.id.chatsRecyclerView))
}
