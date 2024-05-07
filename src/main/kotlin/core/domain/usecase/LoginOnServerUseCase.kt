package core.domain.usecase

import core.data.login.LoginReceiveRemote
import core.data.login.LoginResponseRemote
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.LoginRepository

class LoginOnServerUseCase(private val repository: LoginRepository) {

	suspend fun execute(token: LoginReceiveRemote): Result<LoginResponseRemote, ApiError> {
		return repository.getTokenStatus(token)
	}

}