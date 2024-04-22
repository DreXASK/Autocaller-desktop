package core.domain.repository

import core.data.dto.TokenResponse

interface TokenRepository {
    suspend fun getToken(): TokenResponse
}