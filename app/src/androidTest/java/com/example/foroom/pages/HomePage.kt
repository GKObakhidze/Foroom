package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R as AppR

class HomePage {
    val homeContainer = onView(withId(AppR.id.homeContainer))
    val navBar = onView(withId(AppR.id.navBar))
}
