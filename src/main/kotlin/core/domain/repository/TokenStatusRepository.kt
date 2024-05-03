package core.domain.repository

import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse
import core.domain.ApiError
import core.domain.Result

interface TokenStatusRepository {
    suspend fun getTokenStatus(tokenStatusRequest: TokenStatusRequest): Result<TokenStatusResponse, ApiError>
}