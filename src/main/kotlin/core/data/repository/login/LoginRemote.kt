package core.data.repository.login

import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginTypes
import kotlinx.serialization.Serializable

@Serializable
data class ParameterRemote(
	val token: String
): LoginTypes.Parameter

@Serializable
data class ResponseRemote(
	val tokenStatus: TokenStatus
): LoginTypes.Response
