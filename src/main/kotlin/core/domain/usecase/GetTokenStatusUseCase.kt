package core.domain.usecase

import core.data.dto.TokenStatusRequest
import core.domain.TokenStatus
import core.domain.repository.TokenStatusRepository

class GetTokenStatusUseCase(private val tokenStatusRepository: TokenStatusRepository) {

	suspend fun execute(token: TokenStatusRequest): TokenStatus {
		return tokenStatusRepository.getTokenStatus(token).tokenStatus
	}

}