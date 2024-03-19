package connectionAdjusterScreen.domain

import connectionAdjusterScreen.data.remote.HttpRoutes
import core.domain.ServerConnectionStatus
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlin.text.get

class CheckConnectionStatusUseCase(
	private val client: HttpClient
) {
	suspend fun execute(token: String): ServerConnectionStatus {
		return try {
			client.get {
				url(HttpRoutes.checkTokenStatus)
				setBody(token)
			}.body()
		} catch (e: ResponseException) {
			println(e.response.status.description)
			throw Exception("Ерор)")
		}
	}
}