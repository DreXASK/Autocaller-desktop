package core.data.repository.login

import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginDto
import kotlinx.serialization.Serializable

@Serializable
data class ParameterRemote(
	val token: String
): LoginDto.Parameter

@Serializable
data class ResponseRemote(
	val tokenStatus: TokenStatus
): LoginDto.Response
