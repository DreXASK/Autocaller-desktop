package connectionAdjusterScreen.domain.usecase

import connectionAdjusterScreen.data.remote.ConnectionAdjusterHttpRoutes
import connectionAdjusterScreen.data.remote.dto.ClientTokenRequest
import connectionAdjusterScreen.data.remote.dto.ClientTokenResponse
import connectionAdjusterScreen.data.remote.dto.ClientTokenStatusRequest
import connectionAdjusterScreen.data.remote.dto.ClientTokenStatusResponse
import connectionAdjusterScreen.domain.repository.ClientTokenRepository
import core.domain.ServerConnectionStatus
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

class CheckConnectionStatusUseCase() {
	private val clientTokenRepository by inject<ClientTokenRepository>(ClientTokenRepository::class.java)
	suspend fun execute(token: ClientTokenStatusRequest): ServerConnectionStatus {
		return clientTokenRepository.getTokenStatus(token).body<ClientTokenStatusResponse>().connectionStatus
	}
}