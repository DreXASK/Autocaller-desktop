package connectionAdjusterScreen.data.remote

import connectionAdjusterScreen.data.remote.dto.PostRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {
    override suspend fun getPosts(): HttpResponse {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: ResponseException) {
            // 3xx
            println(e.response.status.description)
            e.response
        }
    }

    override suspend fun createPost(postRequest: PostRequest): HttpResponse {
        return try {
            client.post {
                url(HttpRoutes.POSTS)
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            println(e.response.status.description)
            e.response
        } catch (e: ClientRequestException) {
            // 4xx
            println(e.response.status.description)
            e.response

        } catch (e: ServerResponseException) {
            // 5xx
            println(e.response.status.description)
            e.response
        }
    }

}