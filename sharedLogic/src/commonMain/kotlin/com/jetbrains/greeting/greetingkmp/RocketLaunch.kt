package com.jetbrains.greeting.greetingkmp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunch(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val missionName: String,
    @SerialName("net")
    val launchDateUTC: String,
    @SerialName("status")
    val status: LaunchStatus,
)

@Serializable
data class LaunchStatus(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)

@Serializable
data class LaunchListResponse(
    @SerialName("results")
    val results: List<RocketLaunch>,
)
