
package com.example.foroom

import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext

object TestSessionHelper {

    fun clearSession() {
        val instrumentation =
            InstrumentationRegistry.getInstrumentation()

        val appContext =
            instrumentation.targetContext.applicationContext

        val dataStore =
            GlobalContext.get().get<ForoomUserDataStore>()

        runBlocking {
            dataStore.clearUserData()
        }
    }
}
