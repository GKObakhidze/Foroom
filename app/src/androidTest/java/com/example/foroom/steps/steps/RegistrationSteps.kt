package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {
    // ვაინიციალიზებთ RegistrationPage-ს, რათა მის UI ელემენტებზე გვქონდეს წვდომა
    private val registrationPage = RegistrationPage()

    fun enterUsername(username: String) {
        registrationPage.usernameInput.perform(
            typeText(username),
            closeSoftKeyboard()
        )
    }

    fun enterPassword(password: String) {
        registrationPage.passwordInput.perform(
            typeText(password),
            closeSoftKeyboard()
        )
    }

    fun enterRepeatPassword(password: String) {
        registrationPage.repeatPasswordInput.perform(
            typeText(password),
            closeSoftKeyboard()
        )
    }

    fun selectAvatarFromList() {
        // დავალებაში ნახსენებია: "Authentication and avatar loading are asynchronous."
        // ამიტომ, სანამ ლისტზე დავაკლიკებთ, ტესტების კლასში დაგჭირდება Helper-ის
        // გამოიყენება, რომ დაელოდო ავატარების ჩატვირთვას.
        // უშუალოდ ლისტზე ან მის ელემენტზე დაკლიკება კი ასე ხდება:
        registrationPage.listView.perform(click())
    }

    fun clickSignUp() {
        registrationPage.signUpButton.perform(click())
    }
}