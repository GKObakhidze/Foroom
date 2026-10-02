
package com.example.foroom

import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStoreImpl
import kotlinx.coroutines.runBlocking

object TestSessionHelper {

    fun clearSession() {
        val context =
            InstrumentationRegistry.getInstrumentation()
                .targetContext.applicationContext

        val userDataStore = ForoomUserDataStoreImpl(context)

        runBlocking {
            userDataStore.clearUserData()
        }
    }
}
