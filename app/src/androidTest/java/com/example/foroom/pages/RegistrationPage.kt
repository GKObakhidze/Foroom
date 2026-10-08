
package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.alternator.foroom.R
import com.example.design_system.R as DesignSystemR
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import java.util.concurrent.TimeoutException

class RegistrationPage {

    private val usernameField = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    private val passwordField = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    private val repeatPasswordField = allOf(
        withId(DesignSystemR.id.inputEditText),
        isDescendantOfA(withId(R.id.repeatPasswordInput))
    )

    fun verifyRegistrationScreen() {
        onView(withId(R.id.repeatPasswordInput))
            .check(matches(isDisplayed()))
    }

    fun enterUsername(username: String) {
        onView(usernameField)
            .perform(replaceText(username), closeSoftKeyboard())
    }

    fun enterPassword(password: String) {
        onView(passwordField)
            .perform(replaceText(password), closeSoftKeyboard())
    }

    fun repeatPassword(password: String) {
        onView(repeatPasswordField)
            .perform(replaceText(password), closeSoftKeyboard())
    }

    private fun avatarChooserReadyMatcher(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText(
                    "avatar chooser with at least two images and enabled selection"
                )
            }

            override fun matchesSafely(view: View): Boolean {
                val chooser = view as? ImageChooserListView
                    ?: return false

                return chooser.id == R.id.listView &&
                        chooser.isChoosingEnabled &&
                        chooser.images.size >= 2
            }
        }
    }

    private fun secondAvatarMatcher(): Matcher<View> {
        return allOf(
            isAssignableFrom(ImageChooserItemView::class.java),
            isDescendantOfA(withId(R.id.listView)),
            object : TypeSafeMatcher<View>() {

                override fun describeTo(description: Description) {
                    description.appendText(
                        "second avatar in the avatar list"
                    )
                }

                override fun matchesSafely(view: View): Boolean {
                    val list = generateSequence(view.parent) {
                        it.parent
                    }
                        .filterIsInstance<View>()
                        .firstOrNull { it.id == R.id.listView }
                            as? ImageChooserListView
                        ?: return false

                    val avatarItems = mutableListOf<View>()

                    fun collectAvatars(container: View) {
                        if (container is ImageChooserItemView) {
                            avatarItems.add(container)
                        } else if (container is android.view.ViewGroup) {
                            for (index in 0 until container.childCount) {
                                collectAvatars(
                                    container.getChildAt(index)
                                )
                            }
                        }
                    }

                    collectAvatars(list)

                    return avatarItems.getOrNull(1) === view
                }
            }
        )
    }

    fun selectAvatar() {
        // Wait until avatar selection becomes available.
        waitForView(avatarChooserReadyMatcher())

        // Select the second avatar.
        waitForView(secondAvatarMatcher())
            .perform(click())

        // Verify that the second avatar is selected.
        onView(withId(R.id.listView)).check(
            matches(object : TypeSafeMatcher<View>() {

                override fun describeTo(description: Description) {
                    description.appendText(
                        "second avatar is selected"
                    )
                }

                override fun matchesSafely(view: View): Boolean {
                    val chooser = view as? ImageChooserListView
                        ?: return false

                    return chooser.selectedIndex == 1
                }
            })
        )
    }

    fun clickSignUp() {
        onView(withId(R.id.signUpButton))
            .perform(click())
    }

    fun verifyHomeScreen() {
        waitForView(withId(R.id.homeContainer))
            .check(matches(isDisplayed()))
    }

    private fun waitForView(
        matcher: Matcher<View>,
        timeoutMs: Long = 15_000
    ): ViewInteraction {

        val instrumentation =
            InstrumentationRegistry.getInstrumentation()

        val deadline =
            System.currentTimeMillis() + timeoutMs

        while (System.currentTimeMillis() < deadline) {
            try {
                val interaction = onView(matcher)

                interaction.check(matches(isDisplayed()))

                return interaction

            } catch (_: NoMatchingViewException) {
                // The view is not available yet.
            } catch (_: AssertionError) {
                // The view is not ready yet.
            }

            instrumentation.waitForIdleSync()
            Thread.sleep(250)
        }

        throw TimeoutException(
            "View was not displayed within $timeoutMs ms"
        )
    }
}
