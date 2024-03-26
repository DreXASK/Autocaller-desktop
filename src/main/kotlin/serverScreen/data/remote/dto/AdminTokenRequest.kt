package serverScreen.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminTokenRequest(
	val adminToken: String
)

