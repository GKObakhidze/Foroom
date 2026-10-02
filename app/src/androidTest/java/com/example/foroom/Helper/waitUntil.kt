package com.example.foroom.Helper

fun waitUntil(timeoutMs: Long = 10_000, check: () -> Unit) {
    val end = System.currentTimeMillis() + timeoutMs
    while (true) {
        try { check(); return } catch (e: Throwable) {
            if (System.currentTimeMillis() > end) throw e
            Thread.sleep(250)
        }
    }
}