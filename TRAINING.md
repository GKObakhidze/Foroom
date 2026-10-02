# Foroom Training

The default debug build works locally without an internet connection. Accounts and sessions are stored only on the device. This mode supports login, registration, avatars and sign-out; the home screen has an empty chat list. Chat operations and profile changes are unavailable.

## Run

1. Open the project in Android Studio.
2. Use JDK 17 for Gradle.
3. Select the `app` configuration and `debug` build variant.
4. Run on an emulator or Android device.

The app is installed as **Foroom Training**, separately from the server-connected app.

Demo account: `student` / `Student123!`. Use fictional credentials only.

## Server-connected mode

For a debug build connected to the original server, run:

```sh
./gradlew :app:assembleDebug -PforoomTraining=false
```

Release builds always use the original server. Local accounts are not shared with that server.
