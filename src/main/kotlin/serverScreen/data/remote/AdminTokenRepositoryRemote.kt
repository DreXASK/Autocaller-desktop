package serverScreen.data.remote

import connectionAdjusterScreen.data.remote.ConnectionAdjusterHttpRoutes
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.repository.AdminTokenRepository


class AdminTokenRepositoryRemote(): AdminTokenRepository {
	private val client by inject<HttpClient>(HttpClient::class.java)

	override suspend fun getAdminToken(): HttpResponse {
		return try {
			client.get {
				url(ServerScreenHttpRoutes.getAdminToken)
			}
		} catch (e: ResponseException) {
			println(e.response.status.description)
			e.response
		}
	}
}
