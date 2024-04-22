package serverScreen.domain.repository

import serverScreen.data.remote.dto.TokenResponse

interface TokenRepository {
    suspend fun getToken(): TokenResponse
}