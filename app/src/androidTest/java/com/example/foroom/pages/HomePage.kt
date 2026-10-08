package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class HomePage {
    fun navBar() = onView(withId(R.id.navBar))
}