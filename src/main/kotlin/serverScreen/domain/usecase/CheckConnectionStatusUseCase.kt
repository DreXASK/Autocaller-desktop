package serverScreen.domain.usecase

import serverScreen.data.remote.dto.ClientTokenStatusRequest
import serverScreen.data.remote.dto.ClientTokenStatusResponse
import serverScreen.domain.repository.ClientTokenRepository
import core.domain.ServerConnectionStatus
import io.ktor.client.call.*
import org.koin.java.KoinJavaComponent.inject

class CheckConnectionStatusUseCase() {
	private val clientTokenRepository by inject<ClientTokenRepository>(ClientTokenRepository::class.java)
	suspend fun execute(token: ClientTokenStatusRequest): ServerConnectionStatus {
		return clientTokenRepository.getTokenStatus(token).body<ClientTokenStatusResponse>().connectionStatus
	}
}