package com.jetbrains.simplelogin.kotlinmultiplatformsandbox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform