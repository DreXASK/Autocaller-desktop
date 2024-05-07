package core.domain.usecase

import core.data.repository.login.ParameterRemote
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginRepository

class LoginOnServerUseCase(private val repository: LoginRepository) {

	suspend fun execute(token: ParameterRemote): Result<TokenStatus, ApiError> {
		val result = repository.getTokenStatus(token)
		return result
	}

}