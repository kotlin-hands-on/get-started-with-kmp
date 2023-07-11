package com.jetbrains.simplelogin.kotlinmultiplatformsandbox

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎆"
    }
}