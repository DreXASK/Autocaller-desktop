package core.data

import core.domain.repository.TokenRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import serverScreen.data.remote.ServerScreenHttpRoutes
import core.data.dto.TokenResponse


class TokenRepositoryRemote(private val httpClient: HttpClient): TokenRepository {

	override suspend fun getToken(): TokenResponse {
		return try {
			httpClient.post {
				url(ServerScreenHttpRoutes.GET_TOKEN)
			}.body<TokenResponse>()
		} catch (e: ResponseException) {
			println(e.response.status.description)
			TODO()
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