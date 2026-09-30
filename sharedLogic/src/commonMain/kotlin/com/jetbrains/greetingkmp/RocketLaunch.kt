package com.jetbrains.greetingkmp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @Serializable directs the kotlinx.serialization plugin
// to automatically generate a default serializer for the class
@Serializable
data class RocketLaunch(
    // @SerialName redefines field names, making property names
    // more readable in serialized format
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
