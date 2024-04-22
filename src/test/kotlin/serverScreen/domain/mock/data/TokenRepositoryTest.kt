package serverScreen.domain.mock.data

import serverScreen.data.remote.dto.TokenResponse
import serverScreen.domain.repository.TokenRepository

class TokenRepositoryTest: TokenRepository {

    override suspend fun getToken(): TokenResponse {
        return TokenResponse(token = "TEST")
    }

}