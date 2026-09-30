package com.jetbrains.greetingkmp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlin.time.Instant

class RocketComponent {
    private val httpClient = HttpClient {
        // ContentNegotiation Ktor plugin and the JSON serializer
        // deserialize the result of the GET request
        install(ContentNegotiation) {
            json(Json {
                // Produces more readable JSON
                prettyPrint = true
                // Allows non-standard JSON input,
                // such as unquoted keys and string values
                isLenient = true
                // Ignores keys that haven't been declared in the model
                ignoreUnknownKeys = true
            })
        }
    }

    // Returns the date string for the latest successful launch.
    // Marked as suspending because it calls
    // the suspending httpClient.get() function
    private suspend fun getDateOfLastSuccessfulLaunch(): String {
        // Asynchronously retrieves information about rocket launches
        val response: LaunchListResponse =
            httpClient.get("https://lldev.thespacedevs.com/2.3.0/launches/previous/?mode=list&limit=10&format=json").body()
        // Gets the latest successful launch.
        // In the response launches are sorted from newest to oldest,
        // and successful launches are marked with 'status.id' 3
        val lastSuccessLaunch = response.results.first { it.status.id == 3 }
        // Converts the launch timestamp to local time
        val date = Instant.parse(lastSuccessLaunch.launchDateUTC)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        // Date is displayed in the "MMMM D, YYYY" format,
        // for example, "JULY 15, 2026"
        return "${date.month} ${date.day}, ${date.year}"
    }

    // Builds the final string for the UI using
    // the suspending getDateOfLastSuccessfulLaunch() function
    suspend fun launchPhrase(): String =
        try {
            "The last successful launch was on ${getDateOfLastSuccessfulLaunch()} 🚀"
        } catch (e: Exception) {
            println("Exception during getting the date of the last successful launch $e")
            "Error occurred"
        }
}
