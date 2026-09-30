package com.jetbrains.greetingkmp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val platform = getPlatform()
   
    // Stores the last successful launch date
    private val rocketComponent = RocketComponent()
    // Builds and asynchronously emits greeting strings one by one
    fun greet(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi!" else "Hello!")
        delay(1.seconds)
        emit("Guess what this is! > ${platform.name.reversed()}")
        emit(rocketComponent.launchPhrase())
    }
}
