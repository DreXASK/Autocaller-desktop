package core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
	val token: String
)