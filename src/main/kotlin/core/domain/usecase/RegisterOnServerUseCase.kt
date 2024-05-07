package core.domain.usecase

import core.data.tokens.RegisterReceiveRemote
import core.data.tokens.RegisterResponseRemote
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.RegisterRepository

class RegisterOnServerUseCase(
    private val repository: RegisterRepository
) {

    suspend fun execute(registerReceiveRemote: RegisterReceiveRemote): Result<RegisterResponseRemote, ApiError> {
        return repository.getToken(registerReceiveRemote)
    }

}