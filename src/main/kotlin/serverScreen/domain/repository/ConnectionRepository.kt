package serverScreen.domain.repository

import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.data.remote.dto.ConnectionStatusResponse

interface ConnectionRepository {
    suspend fun getConnectionStatus(tokenDTO: ConnectionStatusRequest): ConnectionStatusResponse
}