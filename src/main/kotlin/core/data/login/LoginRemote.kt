package core.data.login

import core.domain.TokenStatus
import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
	val token: String
)

@Serializable
data class LoginResponseRemote(
	val tokenStatus: TokenStatus
)
