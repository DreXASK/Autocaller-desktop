package core.domain.repository.login

import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.utils.TokenStatus

interface LoginRepository {
    suspend fun getTokenStatus(parameter: LoginTypes.Parameter): Result<TokenStatus, ApiError>
}