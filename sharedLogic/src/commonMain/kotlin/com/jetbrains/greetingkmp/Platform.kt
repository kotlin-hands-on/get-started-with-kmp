package com.jetbrains.greetingkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform