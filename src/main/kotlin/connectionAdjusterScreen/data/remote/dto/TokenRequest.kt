package connectionAdjusterScreen.data.remote.dto

import core.domain.ServerConnectionStatus
import kotlinx.serialization.Serializable

@Serializable
data class TokenRequest(
    val token: String
)