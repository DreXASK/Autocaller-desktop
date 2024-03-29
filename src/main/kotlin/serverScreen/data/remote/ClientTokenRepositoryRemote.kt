package serverScreen.data.remote

import serverScreen.data.remote.dto.ClientTokenStatusRequest
import serverScreen.domain.repository.ClientTokenRepository
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.koin.java.KoinJavaComponent.inject


class ClientTokenRepositoryRemote(): ClientTokenRepository {
	private val client by inject<HttpClient>(HttpClient::class.java)

	override suspend fun getToken(): HttpResponse {
		return try {
			client.get {
				url(ServerScreenHttpRoutes.getToken)
			}
		} catch (e: ResponseException) {
			println(e.response.status.description)
			e.response
		}
	}

	override suspend fun getTokenStatus(tokenDTO: ClientTokenStatusRequest): HttpResponse {
		return try {
			client.get {
				url(ServerScreenHttpRoutes.checkTokenStatus)
				setBody(tokenDTO.token)
			}
		} catch (e: ResponseException) {
			println(e.response.status.description)
			throw Exception("Ерор)")
			TODO("Change that behavior")
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