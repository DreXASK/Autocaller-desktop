package serverScreen.domain.usecase

import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.data.remote.dto.ConnectionStatusResponse
import serverScreen.domain.repository.TokenRepository
import core.domain.ServerConnectionStatus
import io.ktor.client.call.*
import serverScreen.domain.repository.ConnectionRepository

class GetConnectionStatusUseCase(private val connectionRepository: ConnectionRepository) {

	suspend fun execute(token: ConnectionStatusRequest): ServerConnectionStatus {
		return connectionRepository.getConnectionStatus(token).connectionStatus
	}

}