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
			println(e.response.status.description)
			e.response
		}
	}

	override suspend fun createPost(postRequest: PostRequest): HttpResponse {
		return client.post {
			url(HttpRoutes.POSTS)
		}
	}
}