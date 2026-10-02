package com.example.network.training

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import java.security.MessageDigest
import java.util.UUID

/** Local exercise backend. Never forwards a request to the production server. */
class TrainingInterceptor(context: Context) : Interceptor {
    private val preferences = context.getSharedPreferences("foroom_training", Context.MODE_PRIVATE)
    private val packageName = context.packageName

    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val users = JSONObject(preferences.getString("users", "{}")!!)
        if (!users.has("student")) {
            users.put("student", newUser("student", "Student123!", 1))
            save(users)
        }
        val body = request.body?.let { value ->
            val buffer = Buffer()
            value.writeTo(buffer)
            val json = buffer.readUtf8()
            if (json.isBlank()) JSONObject() else JSONObject(json)
        } ?: JSONObject()
        val token = request.header("Authorization")?.removePrefix("Bearer ")
        val currentName = token?.let { preferences.getString("session_$it", null) }
        val current = currentName?.let { users.optJSONObject(it) }
        val path = request.url.encodedPath.lowercase()
        var status = 200
        fun error(code: Int, username: String? = null, password: String? = null): JSONObject {
            status = code
            return JSONObject().apply {
                username?.let { put("usernameError", it) }
                password?.let { put("passwordError", it) }
            }
        }
        fun session(name: String): JSONObject {
            val id = UUID.randomUUID().toString()
            check(preferences.edit().putString("session_$id", name).commit()) {
                "Could not save training session"
            }
            return JSONObject().put("token", id)
        }
        val result: Any = when {
            request.method == "GET" && path == "/api/avatars" -> JSONArray().apply {
                (1..6).forEach { put(JSONObject().put("id", it).put("url", avatar(it))) }
            }
            request.method == "POST" && path == "/api/users/signin" -> {
                val name = body.optString("userName")
                val user = users.optJSONObject(name)
                when {
                    user == null -> error(400, "Username does not exist", "Incorrect password")
                    user.getString("passwordHash") != hash(body.optString("password"), user.getString("salt")) ->
                        error(400, password = "Incorrect password")
                    else -> session(name)
                }
            }
            request.method == "POST" && path == "/api/users/register" -> {
                val name = body.optString("userName")
                val password = body.optString("password")
                val avatarId = body.optInt("avatarId")
                when {
                    name.isBlank() -> error(400, username = "Username is required")
                    users.has(name) -> error(400, username = "Username already exists")
                    password.length < 6 -> error(400, password = "Use at least 6 characters")
                    avatarId !in 1..6 -> error(400, username = "Select an avatar")
                    else -> {
                        users.put(name, newUser(name, password, avatarId))
                        save(users)
                        session(name)
                    }
                }
            }
            current == null -> error(401)
            request.method == "GET" && path == "/api/users/currentuser" -> JSONObject()
                .put("id", current.getString("id"))
                .put("userName", currentName)
                .put("avatarUrl", avatar(current.getInt("avatarId")))
            request.method == "GET" && path == "/api/chats" ->
                JSONObject().put("result", JSONArray()).put("hasNext", false)
            request.method == "POST" && path == "/api/users/signout" -> {
                check(preferences.edit().remove("session_$token").commit()) {
                    "Could not clear training session"
                }
                JSONObject()
            }
            else -> {
                status = 501
                JSONObject().put("message", "This feature is not available in training mode")
            }
        }
        return Response.Builder().request(request).protocol(Protocol.HTTP_1_1)
            .code(status).message(if (status == 200) "OK" else "Training request failed")
            .body(result.toString().toResponseBody("application/json".toMediaType())).build()
    }

    private fun avatar(id: Int) = "android.resource://$packageName/drawable/training_avatar_$id"

    private fun newUser(name: String, password: String, avatarId: Int): JSONObject {
        val salt = UUID.randomUUID().toString()
        return JSONObject().put("id", UUID.randomUUID().toString()).put("userName", name)
            .put("salt", salt).put("passwordHash", hash(password, salt)).put("avatarId", avatarId)
    }

    private fun hash(password: String, salt: String): String =
        MessageDigest.getInstance("SHA-256").digest((salt + password).toByteArray())
            .joinToString("") { "%02x".format(it) }

    private fun save(users: JSONObject) {
        check(preferences.edit().putString("users", users.toString()).commit()) {
            "Could not save training users"
        }
    }
}
