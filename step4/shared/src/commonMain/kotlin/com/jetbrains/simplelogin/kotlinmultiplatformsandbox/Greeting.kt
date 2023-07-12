import com.jetbrains.simplelogin.kotlinmultiplatformsandbox.Platform
import com.jetbrains.simplelogin.kotlinmultiplatformsandbox.RocketLaunch
import com.jetbrains.simplelogin.kotlinmultiplatformsandbox.daysUntilNewYear
import com.jetbrains.simplelogin.kotlinmultiplatformsandbox.getPlatform
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    fun greet(): Flow<List<String>> = flow {
        val greetingMessages: MutableList<String> =
            mutableListOf("Guess what it is! > ${platform.name.reversed()}!")
        greetingMessages.add("There are only ${daysUntilNewYear()} left until New Year! 🎆")
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        greetingMessages.add("The last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀")
        for (i in 0.. greetingMessages.size) {
            // Make a progressive list and delay between each list emission
            val subList = greetingMessages.subList(0, i)
            emit(subList)
            if (i < greetingMessages.size) {
                delay(1000)
            }
        }
    }
}