package core.domain.usecase

import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse
import core.domain.ApiError
import core.domain.Result
import core.domain.TokenStatus
import core.domain.repository.TokenStatusRepository

class GetTokenStatusUseCase(private val repository: TokenStatusRepository) {

	suspend fun execute(token: TokenStatusRequest): Result<TokenStatusResponse, ApiError> {
		return repository.getTokenStatus(token)
	}

}