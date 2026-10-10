package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R as AppR

class ChangeLanguagePage {
    val languageButtonGeo = onView(withId(AppR.id.languageButtonGeo))
    val languageButtonEng = onView(withId(AppR.id.languageButtonEng))
}