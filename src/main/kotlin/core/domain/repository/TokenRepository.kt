package core.domain.repository

import core.data.dto.TokenRequest
import core.data.dto.TokenResponse
import core.domain.ApiError
import core.domain.Result

interface TokenRepository {
    suspend fun getToken(tokenRequest: TokenRequest): Result<TokenResponse, ApiError>
}