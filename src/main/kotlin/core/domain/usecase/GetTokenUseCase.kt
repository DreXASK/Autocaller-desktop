package core.domain.usecase

import core.data.dto.TokenRequest
import core.data.dto.TokenResponse
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.TokenRepository

class GetTokenUseCase(
    private val repository: TokenRepository
) {

    suspend fun execute(tokenRequest: TokenRequest): Result<TokenResponse, ApiError> {
        return repository.getToken(tokenRequest)
    }

}