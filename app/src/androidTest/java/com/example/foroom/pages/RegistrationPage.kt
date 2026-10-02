
package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import java.util.concurrent.TimeoutException

class RegistrationPage {

    private val usernameField = allOf(
        withId(com.example.design_system.R.id.inputEditText),
        isDescendantOfA(withId(R.id.userNameInput))
    )

    private val passwordField = allOf(
        withId(com.example.design_system.R.id.inputEditText),
        isDescendantOfA(withId(R.id.passwordInput))
    )

    private val repeatPasswordField = allOf(
        withId(com.example.design_system.R.id.inputEditText),
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

    private fun secondAvatarMatcher(): Matcher<View> = allOf(
        isAssignableFrom(ImageChooserItemView::class.java),
        isDescendantOfA(withId(R.id.listView)),
        object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText(
                    "second avatar in the avatar list"
                )
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent as? View ?: return false

                val avatarItems = mutableListOf<View>()

                fun collectAvatars(container: View) {
                    if (container is ImageChooserItemView) {
                        avatarItems.add(container)
                    } else if (container is android.view.ViewGroup) {
                        for (index in 0 until container.childCount) {
                            collectAvatars(container.getChildAt(index))
                        }
                    }
                }

                val list = generateSequence(parent) {
                    it.parent as? View
                }.firstOrNull { it.id == R.id.listView }
                    ?: return false

                collectAvatars(list)

                return avatarItems.getOrNull(1) === view
            }
        }
    )

    fun selectAvatar() {
        waitForView(secondAvatarMatcher())
            .perform(click())
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
                // The view has not appeared yet.
            } catch (_: AssertionError) {
                // The view exists but is not displayed yet.
            }

            instrumentation.waitForIdleSync()

            Thread.sleep(250)
        }

        throw TimeoutException(
            "View was not displayed within $timeoutMs ms"
        )
    }
}
