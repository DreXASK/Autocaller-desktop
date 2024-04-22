package core.domain.repository

import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse

interface TokenStatusRepository {
    suspend fun getTokenStatus(tokenStatusRequest: TokenStatusRequest): TokenStatusResponse
}