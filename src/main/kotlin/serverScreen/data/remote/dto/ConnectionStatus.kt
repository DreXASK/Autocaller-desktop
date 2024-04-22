package serverScreen.data.remote.dto

import core.domain.ServerConnectionStatus
import kotlinx.serialization.Serializable

@Serializable
data class ConnectionStatusRequest(
	val token: String
)

@Serializable
data class ConnectionStatusResponse(
	val connectionStatus: ServerConnectionStatus
)
