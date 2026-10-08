package com.example.foroom.Helper

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.constants.TestData
import org.hamcrest.Matcher

fun selectImageAt(index: Int): ViewAction = object : ViewAction {
    override fun getConstraints(): Matcher<View> = isAssignableFrom(ImageChooserListView::class.java)

    override fun getDescription() = "select image at $index"

    override fun perform(uiController: UiController, view: View) {
        val list = view as ImageChooserListView
        var tries = 0
        while (!list.isChoosingEnabled && tries++ < TestData.IMAGE_CHOOSER_MAX_TRIES) {
            uiController.loopMainThreadForAtLeast(TestData.IMAGE_CHOOSER_POLL_MS)
        }
        list.selectImageAt(index)
    }
}