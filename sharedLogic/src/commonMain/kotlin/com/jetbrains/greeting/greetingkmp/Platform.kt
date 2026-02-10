package com.jetbrains.greeting.greetingkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform