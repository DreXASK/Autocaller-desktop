package core.domain.repository

import core.data.login.LoginReceiveRemote
import core.data.login.LoginResponseRemote
import core.domain.ApiError
import core.domain.Result

interface LoginRepository {
    suspend fun getTokenStatus(loginReceiveRemote: LoginReceiveRemote): Result<LoginResponseRemote, ApiError>
}