package connectionAdjusterScreen.data.remote

import connectionAdjusterScreen.data.remote.dto.PostRequest
import connectionAdjusterScreen.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.json.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

interface PostsService {
    suspend fun getPosts(): HttpResponse

    suspend fun createPost(postRequest: PostRequest): HttpResponse

    companion object {
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(CIO) {
//                    install(Logging) {
//                        level = LogLevel.ALL
//                    }
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}