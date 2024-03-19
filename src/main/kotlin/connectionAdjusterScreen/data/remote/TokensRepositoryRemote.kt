package connectionAdjusterScreen.data.remote

import connectionAdjusterScreen.domain.repository.TokensRepository
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class TokensRepositoryRemote(
	private val client: HttpClient
) : TokensRepository {
	override suspend fun getToken(): HttpResponse {
		return try {
			client.get {
				url(HttpRoutes.getToken)
			}
		} catch (e: ResponseException) {
			println(e.response.status.description)
			e.response
		}
	}

/*	override suspend fun sendPendingToken(pendingTokenRequest: PendingTokenRequest): HttpResponse {
		return client.post {
			url(HttpRoutes.POSTS)
			contentType(ContentType.Application.Json)
			setBody(pendingTokenRequest)
		}
	}*/
}