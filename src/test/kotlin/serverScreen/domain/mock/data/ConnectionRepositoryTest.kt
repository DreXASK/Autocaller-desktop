package serverScreen.domain.mock.data

import core.domain.ServerConnectionStatus
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.data.remote.dto.ConnectionStatusResponse
import serverScreen.domain.repository.ConnectionRepository

class ConnectionRepositoryTest: ConnectionRepository {

    override suspend fun getConnectionStatus(tokenDTO: ConnectionStatusRequest): ConnectionStatusResponse {
        return ConnectionStatusResponse(connectionStatus = ServerConnectionStatus.Connected)
    }

}