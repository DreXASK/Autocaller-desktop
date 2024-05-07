package core.data.tokens

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
	val connectionSecretKey: String
)

@Serializable
data class RegisterResponseRemote(
	val token: String
)