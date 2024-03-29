package serverScreen.data.remote.dto

import core.domain.ServerConnectionStatus
import kotlinx.serialization.Serializable

@Serializable
data class ClientTokenStatusRequest(
	val token: String
)

@Serializable
data class ClientTokenStatusResponse(
	val connectionStatus: ServerConnectionStatus
)
