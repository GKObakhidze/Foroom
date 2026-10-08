package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

object ChatsPage {
    val homeNavigationChats = withId(R.id.homeNavigationChats)
    val searchChatInput = withId(R.id.searchChatInput)
    val chatsRecyclerView = withId(R.id.chatsRecyclerView)

    val searchField = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(searchChatInput))

    fun chatCard(name: String): Matcher<View> =
        allOf(withId(DesignR.id.chatTitleTextView), withText(name), isDescendantOfA(chatsRecyclerView))
}