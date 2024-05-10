package core.data.repository.login

import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginTypes
import kotlinx.serialization.Serializable

@Serializable
data class LoginParameterRemote(
	val token: String
): LoginTypes.Parameter

@Serializable
data class LoginResponseRemote(
	val tokenStatus: TokenStatus
): LoginTypes.Response
