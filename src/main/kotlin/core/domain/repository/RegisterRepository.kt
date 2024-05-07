package core.domain.repository

import core.data.tokens.RegisterReceiveRemote
import core.data.tokens.RegisterResponseRemote
import core.domain.ApiError
import core.domain.Result

interface RegisterRepository {
    suspend fun getToken(registerReceiveRemote: RegisterReceiveRemote): Result<RegisterResponseRemote, ApiError>
}